import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KathyGUTest {

    /* TC_1_1 - Тест кейс:
       1. Открыть страницу https://openweathermap.org/
       2. Набрать в строке поиска город Paris
       3. Нажать кнопку Search
       4. Из выпадающего списка выбрать Paris, FR
       5. Подтвердить, что заголовок изменился на "Paris, FR" */

    @Test

    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange:

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        // Act:

        driver.get(url);
        Thread.sleep(7000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();

        Thread.sleep(1000);
        WebElement parisFRChoiceInDropDownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropDownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        // Assert

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    /* TC_11_01
      1. Открыть базовую ссылку
      2. Нажать на пункт меню Guide
      3. Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
      и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap */

    @Test

    public void testPageTitleOpenWeatherMapApiGuide_OpenWeatherMap() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "https://openweathermap.org/guide";
        String expectedResultUrl = "OpenWeatherMap API guide - OpenWeatherMap";

        // Act

        driver.get(url);
        Thread.sleep(5000);

        WebElement GuideButton = driver.findElement(
                By.xpath("//a[@href = '/guide']"));

        GuideButton.click();
        String actualResultTitle = driver.getCurrentUrl();
        String actualResultUrl = driver.getTitle();

        // Assert

        Assert.assertEquals(actualResultTitle,  expectedResultTitle);
        Assert.assertEquals(actualResultUrl, expectedResultUrl);

        driver.quit();
    }

    /* TC_11_02
       Открыть базовую ссылку
       Нажать на единицы измерения Imperial: °F, mph
       Подтвердить, что температура для города показана в Фарингейтах */

    @Test

    public void testTemperatureInFahrenheitAfterClickOnImperial() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String symbolFahrenheit = "°F";

        // Act

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(7000);

        WebElement imperialTab = driver.findElement(By.xpath(
                "//div[@class = 'switch-container']/div[@class='option']/following-sibling::div"
        ));

        imperialTab.click();

        WebElement tempF = driver.findElement(By.xpath(
                "//div[@class='current-temp']/span"));

        // Assert

        Assert.assertTrue(tempF.getText().contains(symbolFahrenheit));

        driver.quit();
    }

    /* TC_11_03
      1.  Открыть базовую ссылку
      2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential
      for the site to work. We also use non-essential cookies to help us improve our services.
      Any data collected is anonymised. You can allow all cookies or manage them individually.”
      3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies” */

    @Test

    public void testCookiesTextAllowAllButtonAndManageCookies_AtPageBottom() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String textCookies = "We use cookies which are essential for the site to work. We also use "
                + "non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.";

        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";

        // Act

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(7000);

        WebElement cookiesPanel = driver.findElement(By.xpath(
                "//p[@class = 'stick-footer-panel__description']"));

        WebElement allowAllButton = driver.findElement(By.xpath(
               "//button[@class = 'stick-footer-panel__link']"));

        WebElement manageCookiesButton = driver.findElement(By.xpath(
                "//a[@href='/cookies-settings']"
        ));

        String actualResultAllowAll = allowAllButton.getText();
        String actualResultManageCookies = manageCookiesButton.getText();

       // Assert

        Assert.assertTrue(cookiesPanel.getText().contains(textCookies));
        Assert.assertEquals(actualResultAllowAll, expectedResultAllowAll);
        Assert.assertEquals(actualResultManageCookies, expectedResultManageCookies);

        driver.quit();
    }

    /* TC_11_04
      1.  Открыть базовую ссылку
      2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”,
      “How to start” и “Ask a question” */

    // first way

    @Test

    public void testFaqHowToStartAskQuestion_InSupportMenu_first () throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String textFaq = "FAQ";
        String textHowToStart = "How to start";
        String textAskQuestion = "Ask a question";

        // Act

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(7000);

        WebElement menuSupport = driver.findElement(By.id("support-dropdown"));
        menuSupport.click();

        // Assert

        Assert.assertEquals(
                driver.findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/li"))
                        .size(), 3);

        Assert.assertTrue(
                driver.findElement(
                        By.xpath("//ul[@id = 'support-dropdown-menu']/li/a[text() = '" + textFaq + "']"
                        )
                ).isDisplayed()
        );

       Assert.assertTrue(
               driver.findElement(
                       By.xpath("//ul[@id = 'support-dropdown-menu']/li/a[text() = '" + textHowToStart + "']"
                        )
               ).isDisplayed()
        );

        Assert.assertTrue(
               driver.findElement(
                       By.xpath("//ul[@id = 'support-dropdown-menu']/li/a[text() = '" + textAskQuestion + "']"
                       )
               ).isDisplayed()
        );

        driver.quit();

    }

    // second way

    @Test

    public void testFaqHowToStartAskQuestion_InSupportMenu_second () throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String expectedResul1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        // Act

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(7000);

        WebElement menuSupport = driver.findElement(By.id("support-dropdown"));
        menuSupport.click();

        Assert.assertEquals(
                driver.findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/*"))
                        .size(), 3);

        String actualResult1 = driver.findElement(By.xpath("//ul[@id = 'support-dropdown-menu']/li[1]")).getText();
        String actualResult2 = driver.findElement(By.xpath("//ul[@id = 'support-dropdown-menu']/li[2]")).getText();
        String actualResult3 = driver.findElement(By.xpath("//ul[@id = 'support-dropdown-menu']/li[3]")).getText();

        // Assert

        Assert.assertEquals(actualResult1, expectedResul1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();
    }

    /* TC_11_05
     1. Открыть базовую ссылку
     2. Нажать пункт меню Support → Ask a question
     3. Заполнить поля Email, Subject, Message
     4. Не подтвердив CAPTCHA, нажать кнопку Submit
     5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.” */

    @Test

    public void testRecaptchaVerificationFailed_WhenCaptchaIsNotSubmitted() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String email = "tester@test.com";
        String message = "Help me";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        // Act

        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(7000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']"));

        menuSupport.click();

        WebElement askQuestionDropDownMenuSupport = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']/li/a[@href = 'https://home.openweathermap.org/questions']"));

        Thread.sleep(5000);
        askQuestionDropDownMenuSupport.click();

        String mainWindows = driver.getWindowHandle(); // getting id of mainWindows;
        for (String windowsHandle: driver.getWindowHandles()) {
            if(!mainWindows.contentEquals(windowsHandle)) {
                driver.switchTo().window(windowsHandle);
                break;
            }
        }

        WebElement emailField = driver.findElement(By.id("question_form_email"));
        emailField.click();
        emailField.sendKeys(email);

        WebElement subjectField = driver.findElement(
                By.xpath("//select[@class = 'form-control select required']"));

        subjectField.click();

        WebElement selectSubject = driver.findElement(By.xpath("//option[@value = 'Sales']"));
        selectSubject.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class = 'form-control text required']"));

        messageField.click();
        messageField.sendKeys(message);

        WebElement buttonSubmit = driver.findElement(
                By.xpath("//input[@data-disable-with = 'Create Question form']")
        );

        buttonSubmit.click();

        String actualResult = driver.findElement(
                By.xpath("//div[@class = 'help-block']")).getText();

        // Assert

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


    /* TC_11_06
      1. Открыть базовую ссылку
      2. Нажать пункт меню Support → Ask a question
      3. Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
      4. Оставить пустым поле Email
      5. Заполнить поля  Subject, Message
      6. Подтвердить CAPTCHA
      7. Нажать кнопку Submit
      8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank” */
    @Test
    public void testErrorEmailAfterSubmit_WhenEmailFieldEmpty() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String message = "Help me";
        String expectedResult = "can't be blank";

        // Act

        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(7000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']"));

        menuSupport.click();

        WebElement askQuestionDropDownMenuSupport = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']/li/a[@href = 'https://home.openweathermap.org/questions']"));

        Thread.sleep(5000);
        askQuestionDropDownMenuSupport.click();

        String mainWindows = driver.getWindowHandle(); // getting id of mainWindows;
        for (String windowsHandle: driver.getWindowHandles()) {
            if(!mainWindows.contentEquals(windowsHandle)) {
                driver.switchTo().window(windowsHandle);
                break;
            }
        }

        WebElement subjectField = driver.findElement(
                By.xpath("//select[@class = 'form-control select required']"));

        subjectField.click();

        WebElement selectSubject = driver.findElement(By.xpath("//option[@value = 'Sales']"));
        selectSubject.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class = 'form-control text required']"));

        messageField.click();
        messageField.sendKeys(message);

        String window2 = driver.getWindowHandle();
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement enterCaptcha = driver.findElement(By.xpath(
                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
                        + "rc-anchor-checkbox']"));
        enterCaptcha.click();

        Thread.sleep(10000);

        driver.switchTo().window(window2);

        WebElement buttonSubmit = driver.findElement(
                By.xpath("//input[@data-disable-with = 'Create Question form']")
        );

        buttonSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(By.xpath("//span[@class='help-block']"));

        String actualResult = confirmErrorEmail.getText();

        // Assert

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /* TC_11_07
      1. Открыть базовую ссылку
      2. Нажать на единицы измерения Imperial: °F, mph
      3. Нажать на единицы измерения Metric: °C, m/s
      4. Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С */

    @Test

    public void testTemperatureSymbolSwitched() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String symbolCelsius = "°C";

        // Act

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement imperialTab = driver.findElement(By.xpath(
                "//div[@class='option'][contains(text(),'°F')]"));

        imperialTab.click();

        Thread.sleep(2000);

        WebElement celsiusTab = driver.findElement(By.xpath(
                "//div[@class='option'][contains(text(),'°C')]"));

        celsiusTab.click();

        Thread.sleep(2000);

        WebElement tempC = driver.findElement(By.xpath(
                "//span[@class = 'heading'][contains(text(),'°C')]")
        );

        // Assert

        Assert.assertTrue(tempC.getText().contains(symbolCelsius));
        driver.quit();
    }

    // second way

    @Test
    public void testTemperatureFormatSelectionInCelsius() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        driver.get(url);
        WebElement switchToFahrenheit = driver.findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']")
        );
        Thread.sleep(7000);
        switchToFahrenheit.click();
        WebElement switchToCelsius = driver.findElement(
                By.xpath("//div[@class='option'][text()='Metric: °C, m/s']")
        );
        switchToCelsius.click();
        char expectedResult = 'C';
        WebElement findCelsius = driver.findElement(
                By.xpath("//span[@class='heading'][contains(text(),'C')]")
        );
        String result = findCelsius.getText();
        char actualResult = result.charAt(result.length()-1);

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /* TC_11_08
      1. Открыть базовую ссылку
      2. Нажать на лого компании
      3. Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась */

    @Test

    public void testUrlNotChanged_AfterClickOnLogo() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/";

        // Act

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(7000);

        WebElement logo = driver.findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
        logo.click();
        Thread.sleep(2000);

        String actualResult = driver.getCurrentUrl();

        // Assert

        Assert.assertEquals(actualResult, expectedResult);

      driver.quit();
    }

    // TC_11_09
    //1.  Открыть базовую ссылку
    //2.  В строке поиска в навигационной панели набрать “Rome"
    //3.  Нажать клавишу Enter
    //4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
    //5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”

    @Test
    public void test_FindRome() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Arrange

        String url = "https://openweathermap.org/";
        String city = "Rome";

        // Act

        driver.get(url);
        Thread.sleep(8000);

        driver.findElement(
                By.xpath("//div[@id='desktop-menu']//input[@placeholder='Weather in your city']")
        ).sendKeys(city + "\n");

        // Assert

        Assert.assertTrue(driver.getCurrentUrl().contains("find") && driver.getCurrentUrl().contains(city));

        driver.quit();
    }
    // TC_11_10
    //1.  Открыть базовую ссылку
    //2.  Нажать на пункт меню API
    //3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
    @Test
    public void testDeskTopMenuClickAPIFind30Buttons1() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        //Arrange
        String url = "https://openweathermap.org/";
        driver.get(url);
        driver.manage().window().maximize();
        int expectedResult = 30;
        Thread.sleep(5000);

        WebElement element = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        element.click();

        int countButton = driver.findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        int actualResult = countButton;

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }
}



