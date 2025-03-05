package utils;

import com.codeborne.selenide.Configuration;
import org.slf4j.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class BaseDriver {

    private static final Logger logger = LoggerFactory.getLogger(BaseDriver.class);
    private static final String DEFAULT_BROWSER = "chrome";
    private static final String DEFAULT_BASE_URL = "https://the-internet.herokuapp.com";
    private static final String DEFAULT_SIZE = "1920x1080";
    private static final long DEFAULT_TIMEOUT = 5000;

    public static void setup() {
        String browser = System.getenv("BROWSER") != null ? System.getenv("BROWSER") : DEFAULT_BROWSER;
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : DEFAULT_BASE_URL;
        String headless = System.getenv("HEADLESS") != null ? System.getenv("HEADLESS") : "false";
        String browserSize = System.getenv("BROWSER_SIZE") != null ? System.getenv("BROWSER_SIZE") : DEFAULT_SIZE;
        long timeout = System.getenv("TIMEOUT") != null ? Long.parseLong(System.getenv("TIMEOUT")) : DEFAULT_TIMEOUT;

        // Настройка Selenide
        Configuration.browser = browser;
        Configuration.baseUrl = baseUrl;
        Configuration.headless = Boolean.parseBoolean(headless); // Headless для CI
        Configuration.browserSize = browserSize;
        Configuration.timeout = timeout;
        Configuration.reportsFolder = "target/selenide-reports"; // Папка для логов/скриншотов

        // Логирование для отладки
        logger.info("Starting tests with browser: {}, baseUrl: {}, headless: {}", browser, baseUrl, headless);
    }

    public static void openPage(String path) {
        logger.info("Opening page: {}", path);
        open(path);
    }

    public static void tearDown() {
        logger.info("Closing WebDriver");
        closeWebDriver();
    }
}