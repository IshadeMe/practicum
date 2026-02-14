package ru.ischade.UrlValidationTest;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UrlValidationTest {

    @TestFactory
    Stream<DynamicTest> testUrlsFromFiles() throws Exception {
        Path urlDirectory = Paths.get("src/test/resources/urls");

        return Files.list(urlDirectory)
                .filter(Files::isRegularFile)
                .map(path -> DynamicTest.dynamicTest("Проверка URL из файла: " + path.getFileName(),
                        () -> {
                            String url = Files.readString(path).trim();
                            assertTrue(url.startsWith("http"));
                        }));
    }
}