package io.github.jairandersonsouza.authorizer.interceptors;

import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfiguration {


    public final static String GENERIC_PROBLEM = "07";
    public final static String REJECTED = "51";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBuilder handleException(final Exception exception) {
        return ResponseBuilder
                .builder()
                .code(GENERIC_PROBLEM)
                .build();
    }

    @ExceptionHandler(AccountNotExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBuilder handleAccountNotExistsException(final AccountNotExistsException accountNotExistsException) {
        return ResponseBuilder
                .builder()
                .code(GENERIC_PROBLEM)
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBuilder handleHttpMessageNotReadableException(
            final HttpMessageNotReadableException exception) {
        final var message = exception.getMessage();
        return ResponseBuilder
                .builder()
                .code(GENERIC_PROBLEM)
                .build();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBuilder handleHttpMediaTypeNotSupportedException(
            final HttpMediaTypeNotSupportedException exception) {
        return ResponseBuilder
                .builder()
                .code(GENERIC_PROBLEM)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBuilder handleConstraintViolationException(
            final ConstraintViolationException exception) {
        return ResponseBuilder
                .builder()
                .code(GENERIC_PROBLEM)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBuilder handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception) {
        return ResponseBuilder
                .builder()
                .code(GENERIC_PROBLEM)
                .build();

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBuilder handleMissingServletRequestParameter(
            final MissingServletRequestParameterException exception) {
        return ResponseBuilder
                .builder()
                .code(GENERIC_PROBLEM)
                .build();
    }

    @ExceptionHandler(TransactionRejectedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBuilder handleTransactionRejectedException(final TransactionRejectedException exception) {
        return ResponseBuilder
                .builder()
                .code(REJECTED)
                .build();
    }


}
