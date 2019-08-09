package com.example.demo;

import com.codeborne.selenide.*;
import com.csvreader.CsvWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
@Service
public class Agent {

    private final String CSVPath = "C:\\Users\\sightna-dev\\Desktop\\csv";

    private WebDriver driver;

    private CsvWriter csvWriter;

    private String ExportDate = "";

    private final String driverPath = "C:\\Windows\\System32\\chromedriver.exe";

    public void run() {
        //agentWebPage();
        agentWebPageSelenide();
        exit();
    }

    public void agentWebPageSelenide() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        Configuration.browser = "chrome";
        Selenide.open("https://github.com");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        SelenideElement searchFiled = Selenide.$(By.xpath("/html/body/div[1]/header/div/div[2]/div[2]/div/div/div/form/label/input[1]"));
        searchFiled.setValue("Selenide");
        Selenide.sleep(1000 * 3);
        searchFiled.sendKeys(Keys.ENTER);
        SelenideElement reportList = Selenide.$(By.xpath("//*[@id=\"js-pjax-container\"]/div/div[3]/div/ul"));
        List<SelenideElement> dataList = reportList.$$(By.cssSelector("[class='repo-list-item d-flex flex-column flex-md-row flex-justify-start py-4 public source']"));

        csvWriter = new CsvWriter(getExportPath(CSVPath), ',', Charset.forName("UTF-8"));
        String[] headers = {"projectName", "projectDescription", "usingLanguage", "starsCount"};
        String[] content = {};
        try {
            csvWriter.writeRecord(headers);
        } catch (Exception e1) {
            System.out.print("CSV Create failed with " + e1);
        }
        for (SelenideElement e : dataList) {
            content = new String[]{
                    getSelenideElementContent(e, By.className("v-align-middle")),
                    getSelenideElementContent(e, By.cssSelector("[class='col-12 col-md-9 d-inline-block text-gray mb-2 pr-4']")),
                    getSelenideElementContent(e.$(By.cssSelector("[class='text-gray flex-auto min-width-0']")), By.className("mr-3")),
                    getSelenideElementContent(e.$(By.cssSelector("[class='pl-2 pl-md-0 text-right flex-auto min-width-0']")), By.className("muted-link"))
            };
            try {
                csvWriter.writeRecord(content);
            } catch (Exception e1) {
                System.out.print("Export CSV failed with " + e1);
            }
        }

    }

    public void agentWebPage() {

        driver = new ChromeDriver();
        driver.get("https://github.com");
        driver.manage().window().maximize();
        WebElement searchFiled = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div[2]/div/div/div/form/label/input[1]"));
        searchFiled.sendKeys("Selenide");

        searchFiled.sendKeys(Keys.ENTER);
        WebElement repoList = driver.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div/div[3]/div/ul"));
        List<WebElement> dataList = repoList.findElements(By.cssSelector("[class='repo-list-item d-flex flex-column flex-md-row flex-justify-start py-4 public source']"));

        csvWriter = new CsvWriter(getExportPath(CSVPath), ',', Charset.forName("UTF-8"));
        String[] headers = {"projectName", "projectDescription", "usingLanguage", "starsCount"};
        String[] content = {};
        try {
            csvWriter.writeRecord(headers);
        } catch (Exception e1) {
            System.out.print("CSV Create failed with " + e1);
        }
        for (WebElement e : dataList) {
            String projectName = getElementContent(e, By.className("v-align-middle"));
            String projectDescription = getElementContent(e, By.cssSelector("[class='col-12 col-md-9 d-inline-block text-gray mb-2 pr-4']"));
            String usingLanguage = getElementContent(e.findElement(By.cssSelector("[class='text-gray flex-auto min-width-0']")), By.className("mr-3"));
            String starsCount = getElementContent(e.findElement(By.cssSelector("[class='pl-2 pl-md-0 text-right flex-auto min-width-0']")), By.className("muted-link"));
            content = new String[]{projectName, projectDescription, usingLanguage, starsCount};
            try {
                csvWriter.writeRecord(content);
            } catch (Exception e1) {
                System.out.print("Export CSV failed with " + e1);
            }

        }

    }

    public String getSelenideElementContent(SelenideElement element, By locator) {
        try {
            SelenideElement e = element.$(locator);
            if (e != null && e.exists()) {
                return e.getText();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public String getElementContent(WebElement element, By locator) {
        try {
            WebElement e = element.findElement(locator);
            if (e != null) {
                return e.getText();
                //element.getAttribute("text");
            }
        } catch (Exception e) {
            System.out.println("Element:" + locator.toString()
                    + " is not exsit!");
        }

        return null;
    }

    public String getExportPath(String csvpath) {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String dateformat = SDF.format(date);
        return csvpath + "\\" + dateformat + ".csv";
    }

    public void exit() {
        try {
            csvWriter.close();
            driver.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
