package core.basesyntax.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReaderServiceImplTest {
    @Test
    void readFromFile_OK() {
        String filePath = "test.csv";
        String date = "type,fruit,quantity\n"
                + "b,banana,20\n"
                + "b,apple,100\n";
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(date);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ReaderServiceImpl readerService = new ReaderServiceImpl();
        List<String> lines = readerService.readFromFile(filePath);
        Assertions.assertEquals(3, lines.size());
        Assertions.assertEquals("type,fruit,quantity", lines.get(0));
        Assertions.assertEquals("b,banana,20", lines.get(1));
        Assertions.assertEquals("b,apple,100", lines.get(2));
    }

    @Test
    void readFromNonExistentFile_NotOk() {
        ReaderServiceImpl readerService = new ReaderServiceImpl();
        String nonExistentFilePath = "non-existent-file.csv";
        Assertions.assertThrows(RuntimeException.class,
                () -> readerService.readFromFile(nonExistentFilePath));
    }
}