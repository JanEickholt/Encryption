package src;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Whatsapp extends JFrame {
    static Contact contact = new Contact();
    public static int width = 1500;
    public static int height = 1000;
    public Whatsapp() throws IOException {
        UI ui = new UI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(new Font("Roboto", Font.PLAIN, 20));
        setTitle("Whatsapp illegal thing");
        setIconImage(new ImageIcon("src/images/icon.png").getImage());
        setContentPane(ui.content_pane);
        setResizable(false);
        setBounds(300, 50, width, height);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Whatsapp frame = new Whatsapp();
        frame.setVisible(true);

        System.setProperty("webdriver.gecko.driver", "./assets/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        options.setHeadless(true);
        FirefoxDriver driver = new FirefoxDriver(options);
        driver.get("https://web.whatsapp.com/");
        driver.manage().window().maximize();

        boolean first_page_loaded = false;
        while (!first_page_loaded) {
            try {
                driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[1]/div/div[2]/div/canvas"));
                first_page_loaded = true;
                System.out.println("QR code loaded");
            } catch (Exception e) {
                synchronized (driver) {
                    driver.wait(50);
                }
            }
        }

        WebElement webElement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[1]/div/div[2]/div/canvas"));
        File scrFile = ((TakesScreenshot) webElement).getScreenshotAs(OutputType.FILE);
        byte [] bytes = FileUtils.readFileToByteArray(scrFile);
        UI.changeToMainScreen(bytes);

        boolean is_logged_in = false;
        while (!is_logged_in) {
            try {
                driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[3]/header"));
                is_logged_in = true;
                System.out.println("Logged in");
            }
            catch (Exception e) {
                synchronized (driver) {
                    driver.wait(50);
                }
            }
        }
        synchronized (driver) {
            driver.wait(1000);
        }
        getContacts(driver);
        UI.changeToContactsScreen(Contact.sorted_contacts);
    }


    public static void clickOnChat(FirefoxDriver driver, String chatName) {
        Document doc = Jsoup.parse(driver.getPageSource());

        Elements first = doc.getElementsByClass("lhggkp7q ln8gz9je rx9719la");
        for (Element element: first) {
            String name;
            for (Element child: element.children()) {
                for (Element el: child.getElementsByClass("ggj6brxn gfz4du6o r7fjleex g0rxnol2 lhj4utae le5p0ye3 l7jjieqr i0jNr")) {
                    System.out.println("name: " + el.attr("title"));
                    name = el.attr("title");
                    if (name.equals(chatName)) {
                        driver.findElement(By.cssSelector(child.cssSelector())).click();
                    }
                    break;
                }
            }
        }
    }

    public static void getMessages(FirefoxDriver driver) {
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements first = doc.getElementsByClass("_1-lf9 _3mSPV _18q-J");
        for (Element element: first) {

            if (element.parent().className().equals("_1-FMR message-out focusable-list-item")) {
                System.out.println("me");
                System.out.println("out");
            } else {
                System.out.println("in");

                for (Element child_msg: element.getElementsByClass("a71At ajgl1lbb edeob0r2 i0jNr")) {
                    System.out.println(child_msg.ownText());
                    break;
                }
            }

            for (Element child_msg: element.getElementsByClass("i0jNr selectable-text copyable-text")) {
                System.out.println(child_msg.text());
                break;

            }
        }
    }
    public static void getContacts(FirefoxDriver driver) throws IOException {
        // TODO parse something to identify chat - possibility to click & fetch
        // TODO scroll

        Document doc = Jsoup.parse(driver.getPageSource());
        System.out.println("started");

        Elements first = doc.getElementsByClass("lhggkp7q ln8gz9je rx9719la");
        for (Element element: first) {
            int y_level;
            String name = null;
            String profile_picture_url = null;

            y_level = Integer.parseInt(element.attr("style").split("(translateY)")[1].replace("(", "").replace(")", "").replace(";", "").replace("px", ""));

            for (Element child: element.children()) {
                Elements images_elements = child.getElementsByClass("_3GlyB");
                for (Element element2: images_elements) {
                    for (Element child_element: element2.children()) {
                        if (!child_element.attr("src").equals("")) {
                            profile_picture_url = child_element.attr("src");
                            break;
                        }
                    }
                }

                for (Element el: child.getElementsByClass("ggj6brxn gfz4du6o r7fjleex g0rxnol2 lhj4utae le5p0ye3 l7jjieqr i0jNr")) {
                    name = el.attr("title");
                    break;
                }
            }
            contact.createContact(y_level, name, profile_picture_url);
        }
    }

}