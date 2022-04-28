package vu.mif.saltenis.bartas.softwaredesign.persistence;

import org.junit.jupiter.api.*;
import vu.mif.saltenis.bartas.softwaredesign.domain.user.User;
import vu.mif.saltenis.bartas.softwaredesign.domain.user.UserBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private static final String storagePathName = "src/test/resources/user_sequence.txt";
    private static final String sequencePath = "src/test/resources/data.txt";

    private File storageFile;
    private File sequenceFile;

    private BufferedWriter storageWriter;
    private BufferedWriter sequenceWriter;

    private UserRepository userRepository;

    @BeforeEach
    public void init() throws IOException {
        this.storageFile = new File(storagePathName);
        this.sequenceFile = new File(sequencePath);

        this.storageWriter = new BufferedWriter(new FileWriter(storageFile, true));
        this.sequenceWriter = new BufferedWriter(new FileWriter(sequenceFile, true));

        this.userRepository = new UserRepository(storageFile, new GeneratedValue(sequenceFile));

        storageWriter.write("1 FirstName LastName +37064333333 test@test.com addr 123aA???\n");
        storageWriter.write("2 FirstName LastName +37064333333 test@test.com addr 123aA???\n");
        storageWriter.write("3 FirstName LastName +37064333333 test@test.com addr 123aA???\n");
        storageWriter.write("4 FirstName LastName +37064333333 test@test.com addr 123aA???\n");
        storageWriter.write("5 FirstName LastName +37064333333 test@test.com addr 123aA???\n");
        storageWriter.close();

        sequenceWriter.write("5");
        sequenceWriter.close();
    }

    @AfterEach
    public void teardown() {
        storageFile.delete();
        sequenceFile.delete();
    }

    @Test
    void ifNoIdFound_shouldReturnNull() {
        User user = userRepository.getById(10);

        assertNull(user);
    }

    @Test
    void whenRemoved_getByIdShouldReturnNull() throws IOException {
        User user = new UserBuilder("Test", "Test").build();
        user.setId(5);

        userRepository.removeAndFlush(user);

        assertNull(userRepository.getById(5));
    }

    @Test
    void ifTryToDeleteNonExistingEntity_shouldNotModifyFile() throws IOException {
        User user = new UserBuilder("Test", "Test").build();
        user.setId(10);

        userRepository.removeAndFlush(user);

        assertEquals(5, Files.readAllLines(storageFile.toPath(), StandardCharsets.UTF_8).size());
    }

    @Test
    void whenNewEntityIsSaved_shouldAssignedCorrectIdFromSequenceFile() throws IOException {
        User user = userRepository.getById(1);

        userRepository.saveAndFlush(user);

        assertEquals("6", Files.readAllLines(storageFile.toPath(), StandardCharsets.UTF_8).get(5).split(" ")[0]);
    }
}
