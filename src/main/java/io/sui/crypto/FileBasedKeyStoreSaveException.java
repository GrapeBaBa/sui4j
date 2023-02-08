package io.sui.crypto;

/**
 * @author fearlessfe
 * @since 2023 02
 */
public class FileBasedKeyStoreSaveException extends RuntimeException{

    /**
     * Instantiates a new File based key store save exception.
     *
     * @param cause the cause
     */
    public FileBasedKeyStoreSaveException(Throwable cause) {
        super(cause);
    }
}
