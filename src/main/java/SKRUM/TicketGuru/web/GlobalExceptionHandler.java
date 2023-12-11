package SKRUM.TicketGuru.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import SKRUM.TicketGuru.domain.exceptions.BadCredentialsCustomException;
import SKRUM.TicketGuru.domain.exceptions.CustomForbiddenException;
import SKRUM.TicketGuru.domain.exceptions.CustomerNotFoundException;
import SKRUM.TicketGuru.domain.exceptions.EventNotFoundException;
import SKRUM.TicketGuru.domain.exceptions.TicketTypeNotFoundException;
import SKRUM.TicketGuru.domain.response.ErrorRes;
import SKRUM.TicketGuru.domain.response.ValidErrorRes;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorRes> handleEntityNotFoundException(EntityNotFoundException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidErrorRes> handleConstraintViolationException(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();

        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errors.add(String.format("Field '%s' %s", fieldError.getField(), fieldError.getDefaultMessage()));
            } else {
                errors.add(error.getDefaultMessage());
            }
        }
        ValidErrorRes errorResponse = new ValidErrorRes(HttpStatus.BAD_REQUEST, "Validation errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsCustomException.class)
    public ResponseEntity<ErrorRes> handleBadCredentialsException(BadCredentialsCustomException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorRes> handleInternalAuthenticationServiceException(
            InternalAuthenticationServiceException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(TicketTypeNotFoundException.class)
    public ResponseEntity<ErrorRes> handleTicketTypeNotFoundException(TicketTypeNotFoundException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorRes> handleEventNotFoundException(EventNotFoundException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorRes> handleCustomerNotFoundException(CustomerNotFoundException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorRes> handleRuntimeException(RuntimeException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ValidErrorRes> handleTransactionSystemException(TransactionSystemException e) {
        Throwable cause = e.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            List<String> errors = new ArrayList<>();
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause).getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                errors.add(String.format("Field '%s' %s", violation.getPropertyPath(), violation.getMessage()));
            }
            ValidErrorRes errorResponse = new ValidErrorRes(HttpStatus.BAD_REQUEST, "Validation errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } else {
            // handle other causes of TransactionSystemException
            ValidErrorRes errorResponse = new ValidErrorRes(HttpStatus.BAD_REQUEST, "An error occurred",
                    new ArrayList<>());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ErrorRes> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,
                "customer name and/or email or id input is missing");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CustomForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ResponseEntity<ErrorRes> handleCustomForbiddenException(CustomForbiddenException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.FORBIDDEN, e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorRes> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,
                "Conflicting values inserted, cannot insert into database");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRes> handleException(Exception e) {
        ErrorRes errorResponse = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}