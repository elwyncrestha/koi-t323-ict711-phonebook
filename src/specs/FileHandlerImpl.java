package specs;

import static constant.AppConstant.FILE_STORAGE_LOCATION;

import java.io.File;
import java.util.Optional;

/**
 * This class defines the methods used for File handling (I/O).
 */
public abstract class FileHandlerImpl<T> implements FileHandler<T> {

    private final String fileName;
    protected T data;

    protected FileHandlerImpl(String fileName) {
        this.fileName = fileName;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String buildPath() {
        return String.join("/", FILE_STORAGE_LOCATION, fileName);
    }

    public Optional<File> readFile() {
        File file = new File(buildPath());
        if (!file.exists()) {
            return Optional.empty();
        }

        return Optional.of(file);
    }
}
