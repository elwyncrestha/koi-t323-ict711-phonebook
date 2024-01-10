package specs;

import static constant.AppConstant.FILE_STORAGE_LOCATION;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import exception.UnknownPropertyException;

/**
 * This class defines the methods used for File handling (I/O).
 */
public abstract class FileHandlerImpl<T> implements FileHandler<T> {

    private final String fileName;
    protected List<T> data;

    protected FileHandlerImpl(String fileName) {
        this.fileName = fileName;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String buildPath() {
        return String.join("/", FILE_STORAGE_LOCATION, fileName);
    }

    @Override
    public Optional<File> readFile() {
        File file = new File(buildPath());
        if (!file.exists()) {
            return Optional.empty();
        }

        return Optional.of(file);
    }

    @Override
    public void writeFile(List<String> contents) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(buildPath());
        for (String content: contents) {
            printWriter.println(content);
        }
        printWriter.close();
    }

    /**
     * This deserialization works under the assumption that each instruction
     * is separated by an empty line.
     */
    @Override
    public List<T> deserialize(File file)
        throws FileNotFoundException, DateTimeParseException, UnknownPropertyException {
        List<T> list = new ArrayList<>();

        try (final Scanner scanner = new Scanner(file)) {

            T t = null;
            boolean hasNextLine = scanner.hasNextLine();
            while (hasNextLine) {
                String line = scanner.nextLine();

                if (t != null && line.trim().isEmpty()) {
                    list.add(t);
                    t = null;
                    continue;
                }

                if (t == null) {
                    t = getInstance();
                }
                mapContentToModel(t, line);

                hasNextLine = scanner.hasNextLine();
                if (!hasNextLine) {
                    list.add(t);
                }
            }
        }
        return list;
    }
}
