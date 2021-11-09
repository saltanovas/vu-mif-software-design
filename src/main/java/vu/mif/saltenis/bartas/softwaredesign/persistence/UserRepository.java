package vu.mif.saltenis.bartas.softwaredesign.persistence;

import vu.mif.saltenis.bartas.softwaredesign.domain.user.User;
import vu.mif.saltenis.bartas.softwaredesign.domain.user.UserBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepository implements IBaseRepository<User> {
    private static final String storagePathName = System.getenv("DB_FILE_PATH");
    private static final String sequencePath = System.getenv("USER_SEQUENCE_FILE_PATH");

    private final GeneratedValue idGenerator;
    private final File file;
    private final Scanner scanner;

    public UserRepository() throws IOException {
        this.idGenerator = new GeneratedValue(new File(sequencePath));
        this.file = new File(storagePathName);
        file.createNewFile();
        this.scanner = new Scanner(file);
    }

    @Override
    public User getById(int id) {
        while (scanner.hasNextLine()) {
            String[] user = scanner.nextLine().split(" ");

            if (user[0].equals(String.valueOf(id))) {
                return new UserBuilder(user[1], user[2])
                        .setPhoneNumber(user[3])
                        .setEmail(user[4])
                        .setAddress(user[5])
                        .setPassword(user[6])
                        .build();
            }
        }

        return null;
    }

    @Override
    public void removeAndFlush(User entity) throws IOException {
        var users = Files.lines(file.toPath())
                .filter(line -> !line.split(" ")[0].equals(String.valueOf(entity.getId())))
                .collect(Collectors.toList());

        Files.write(file.toPath(), users, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public void saveAndFlush(User entity) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));

        int line;
        for (line = 0; line < fileContent.size(); line++) {
            if (fileContent.get(line).split(" ")[0].equals(String.valueOf(entity.getId()))) {
                fileContent.set(line, entity.toString().replace("\n", ""));
                break;
            }
        }

        if(line >= fileContent.size()) {
            entity.setId(idGenerator.generateId());
            Files.write(file.toPath(), entity.toString().getBytes(), StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } else {
            Files.write(file.toPath(), fileContent, StandardCharsets.UTF_8);
        }
    }
}
