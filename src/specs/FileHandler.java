package specs;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import exception.UnknownPropertyException;

/**
 * This interface defines specification for File Handling (I/O) in the app.
 */
public interface FileHandler<T> {

    /**
     * A method to build a path to the file.
     *
     * @return A final file path.
     */
    String buildPath();

    /**
     * A method to read a file.
     *
     * @return A file or an empty instance if file is not found.
     */
    Optional<File> readFile();

    /**
     * A method that returns a new empty instance of {@link T}.
     *
     * @return A new instance of {@link T}
     */
    T getInstance();

    /**
     * A method to map the content to the model.
     *
     * @param model   An instance of the model whose properties are to be updated.
     * @param content A string content.
     */
    void mapContentToModel(T model, String content)
        throws DateTimeParseException, UnknownPropertyException;

    /**
     * A method to deserialize the file contents.
     *
     * @param file A file instance.
     * @return The deserialized instance.
     */
    List<T> deserialize(File file)
        throws FileNotFoundException, DateTimeParseException, UnknownPropertyException;

}
