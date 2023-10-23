package SKRUM.TicketGuru.domain.exceptions;

public class CustomForbiddenException extends RuntimeException {
    public CustomForbiddenException(String message) {
        super(message);
    }
}