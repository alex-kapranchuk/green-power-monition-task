package utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotHelper {

    private static final Logger logger = LogManager.getLogger(ScreenshotHelper.class);
    private static final Path SCREENSHOTS_DIR = Paths.get("target/screenshots");

    static {
        try {
            Files.createDirectories(SCREENSHOTS_DIR);
        } catch (IOException e) {
            logger.error("Failed to create screenshots directory", e);
        }
    }

    public static void attachScreenshot(Page page, String name) {
        try {
            byte[] screenshot = page.screenshot();
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));

            // Save screenshot to the temporary directory
            Path screenshotPath = SCREENSHOTS_DIR.resolve(name + ".png");
            Files.write(screenshotPath, screenshot);
            logger.info("Screenshot '{}' saved to {}", name, screenshotPath);
        } catch (Exception e) {
            logger.error("Failed to take or save screenshot '{}': {}", name, e.getMessage());
        }
    }
}
