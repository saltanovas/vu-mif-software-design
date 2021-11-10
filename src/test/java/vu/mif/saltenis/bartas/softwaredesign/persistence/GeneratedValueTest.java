package vu.mif.saltenis.bartas.softwaredesign.persistence;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class GeneratedValueTest {

    private static final String pathname = "src/test/resources/user_sequence.txt";
    private File file;
    private BufferedWriter bufferedWriter;

    @BeforeEach
    public void init() throws IOException {
        this.file = new File(pathname);
        this.bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    }

    @AfterEach
    public void teardown() {
        file.delete();
    }

    @Test
    void whenNewInstanceIsCreated_shouldCreateNewFile() throws IOException {
        new GeneratedValue(file);

        assertTrue(file.exists());
    }

    @Test
    void whenFileIsNotCreatedOrEmpty_shouldReturnOne() throws IOException {
        int id = new GeneratedValue(file).generateId();

        assertEquals(1, id);
    }

    @Test
    void whenFileContainsNumberFive_shouldReturnSix() throws IOException {
        bufferedWriter.write("5");
        bufferedWriter.close();

        int id = new GeneratedValue(file).generateId();

        assertEquals(6, id);
    }

    @Test
    void whenFileContainsText_shouldReturnOne() throws IOException {
        bufferedWriter.write("unit\ntesting");
        bufferedWriter.close();

        int id = new GeneratedValue(file).generateId();

        assertEquals(1, id);
    }
}
