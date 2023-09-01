package tech.ada.bootcamp.arquitetura.cartaoservice.exceptions;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
