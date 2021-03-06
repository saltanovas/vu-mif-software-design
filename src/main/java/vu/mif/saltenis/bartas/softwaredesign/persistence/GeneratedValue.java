package vu.mif.saltenis.bartas.softwaredesign.persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class GeneratedValue {
    private final File file;
    private final BufferedReader bufferedReader;

    public GeneratedValue(File file) throws IOException {
        this.file = file;
        file.createNewFile();
        this.bufferedReader = new BufferedReader(new FileReader(file));
    }

    public int generateId() throws IOException {
        String number = bufferedReader.readLine();

        try {
            number = String.valueOf(Integer.parseInt(number) + 1);
        } catch (NumberFormatException e) {
            number = "1";
        }

        Files.write(file.toPath(), number.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

        return Integer.parseInt(number);
    }


}
