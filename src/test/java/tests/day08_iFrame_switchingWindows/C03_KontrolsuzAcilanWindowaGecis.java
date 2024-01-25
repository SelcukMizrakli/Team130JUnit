package tests.day08_iFrame_switchingWindows;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import utilities.ReusableMethods;
import utilities.TestBase;

import java.util.List;
import java.util.Set;

public class C03_KontrolsuzAcilanWindowaGecis extends TestBase {
    @Test
    public void kontrolsuzWindowTesti() {

        //1- https://testotomasyonu.com/discount adresine gidin
        driver.get("https://testotomasyonu.com/discount");
        //2- Elektronics Products yazisinin gorunur olduğunu test edin
        //  yazi iframe icinde oldugundan, once o iframe'i locate edip, oraya gecelim

        WebElement iFrameElementi = driver.findElement(By.xpath("(//iframe)[1]"));
        driver.switchTo().frame(iFrameElementi);

        WebElement electronicsYaziElementi = driver.findElement(By.xpath("//*[text()='Electronics Products']"));
        Assert.assertTrue(electronicsYaziElementi.isDisplayed());

        //3- Dell bilgisayar urun isminin ‘DELL Core I3 11th Gen’ olduğunu test edin

        WebElement dellElementi = driver.findElement(By.xpath("//*[text()='DELL Core I3 11th Gen ']"));
        String expectedUrunIsmi = "DELL Core I3 11th Gen";
        String actualUrunIsmi = dellElementi.getText();
        Assert.assertEquals(expectedUrunIsmi, actualUrunIsmi);

        // click yapildiginda, kontrolsuz olarak yeni bir tab acildigindan
        // driver yeni tab'a gecmez, eski window'da kalir.
        // bu durumda ikinci sayfanin window handle degerini bulabilmek icin
        // 3 adima ihtiyacimiz var

        // 1. adim : ilk window'un WHD'ini kaydedelim
        String ilkWindowWHD = driver.getWindowHandle();

        //4- Dell bilgisayar’a tiklayip acilan sayfada urun fiyatinin $399.00 olduğunu test edin.

        dellElementi.click();
        // 2.adim : click yapildiktan sonra WHD'ini kaydettigimniz ilk window'un yanina
        //  yeni bir tab acildi.
        //          getWindowHandles() kullanarak acik olan iki window'un
        //          WHD'lerini bir Set olarak kaydedelim.
        Set<String> WhDegerleriSeti = driver.getWindowHandles();
        String ikinciWindowWHD = null;
        for (String each : WhDegerleriSeti) {
            if (!each.equals(ilkWindowWHD)) {
                ikinciWindowWHD = each;
            }
        }
        driver.switchTo().window(ikinciWindowWHD);
        String expectedPrice = "$399.00";
        String actualPrice = driver.findElement(By.xpath("//*[@id='priceproduct']")).getText();

        Assert.assertEquals(expectedPrice, actualPrice);

        //5- Ilk sayfaya donun ve Fashion yazisinin gorunur olduğunu test edin

        driver.switchTo().defaultContent();
        driver.switchTo().window(ilkWindowWHD);

        iFrameElementi = driver.findElement(By.xpath("(//iframe)[2]"));
        driver.switchTo().frame(iFrameElementi);

        WebElement fashionYaziElementi = driver.findElement(By.xpath("//*[text()='Fashion']"));
        Assert.assertTrue(fashionYaziElementi.isDisplayed());

        //6- Sayfayi kapatin
        ReusableMethods.bekle(2);
    }
}
