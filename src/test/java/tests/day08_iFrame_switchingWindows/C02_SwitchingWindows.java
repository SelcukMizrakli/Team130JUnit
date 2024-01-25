package tests.day08_iFrame_switchingWindows;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WindowType;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C02_SwitchingWindows extends TestBase {

    @Test
    public void yeniWindowTesti() {

        /*
            Bir sayfada test yaparken
            driver.switchTo().newWindow(...); kullandigimizda
            yeni window'u driver'a olusturttugumuz icin
            driver otomatik olarak yeni sayfaya gecer

            driver yeni window'a gectikten sonra
            eski window'lardan birine donmesi istenilecekse
            donulecek window'da iken o sayfanin windowHandleDegerini alip kaydetmeliyiz.

            driver.switchTo.window(kaydedilenWindowHandelDegeri); ile o sayfaya donebiliriz.

        */

        //● testotomasyonu anasayfa adresine gidin.
        driver.get("https://www.testotomasyonu.com");
        //● Sayfa’nin window handle degerini String bir degiskene atayin
        String toWindowHandleDegeri = driver.getWindowHandle();
        //● Sayfa title’nin “Otomasyon” icerdigini test edin
        String expectedTitleIcerik = "Otomasyon";
        String actualTitleIcerik = driver.getTitle();

        Assert.assertTrue(actualTitleIcerik.contains(expectedTitleIcerik));
        ReusableMethods.bekle(2);
        //● Yeni bir tab olusturup, acilan tab’da wisequarter.com adresine gidin
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://wisequarter.com");
        //● Sayfa title’nin “wise quarter” icerdigini test edin
        expectedTitleIcerik = "Wise Quarter";
        actualTitleIcerik = driver.getTitle();

        Assert.assertTrue(actualTitleIcerik.contains(expectedTitleIcerik));
        ReusableMethods.bekle(2);
        //● Yeni bir window olusturup, acilan sayfada walmart.com adresine gidin
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://walmart.com");
        //● Sayfa title’nin “Walmart” icerdigini test edin
        expectedTitleIcerik = "Walmart";
        actualTitleIcerik = driver.getTitle();

        Assert.assertTrue(actualTitleIcerik.contains(expectedTitleIcerik));
        ReusableMethods.bekle(2);
        //● Ilk acilan sayfaya donun ve testotomasyonu sayfasina dondugunuzu test edin
        driver.switchTo().window(toWindowHandleDegeri);
        expectedTitleIcerik = "Otomasyon";
        actualTitleIcerik = driver.getTitle();

        Assert.assertTrue(actualTitleIcerik.contains(expectedTitleIcerik));

        ReusableMethods.bekle(2);
    }
}
