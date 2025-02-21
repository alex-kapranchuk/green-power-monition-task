package launch;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestResultListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestResultListener.class);
    private static final Path ZIP_FILE = Paths.get("target/test-results.zip");
    private static final Path SCREENSHOTS_DIR = Paths.get("target/screenshots");
    private static final Path LOGS_DIR = Paths.get("target/logs");

    @Override
    public void onFinish(ITestContext context) {
        try {
            zipTestResults();
            try (FileInputStream fis = new FileInputStream(ZIP_FILE.toFile())) {
                Allure.addAttachment("Test Results Archive", fis);
            }
        } catch (IOException e) {
            logger.error("Failed to archive or attach test results", e);
        }
    }

    private void zipTestResults() throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(ZIP_FILE))) {

            // Add screenshots
            if (Files.exists(SCREENSHOTS_DIR)) {
                Files.walkFileTree(SCREENSHOTS_DIR, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        zos.putNextEntry(new ZipEntry("screenshots/" + SCREENSHOTS_DIR.relativize(file).toString()));
                        Files.copy(file, zos);
                        zos.closeEntry();
                        return FileVisitResult.CONTINUE;
                    }
                });
            }

            // Add logs
            if (Files.exists(LOGS_DIR)) {
                Files.walkFileTree(LOGS_DIR, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        zos.putNextEntry(new ZipEntry("logs/" + LOGS_DIR.relativize(file).toString()));
                        Files.copy(file, zos);
                        zos.closeEntry();
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
            // Add an allure-report or other artifacts...
        }
    }
}