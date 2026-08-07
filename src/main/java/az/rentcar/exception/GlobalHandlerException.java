package az.rentcar.exception;

import az.rentcar.dto.errorDto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {

    private static final Marker SILENT = MarkerFactory.getMarker("SILENT");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        log.error("""
            HTTP {} {}
            Status : {}
            Message: {}
            """,
                request.getMethod(),
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                ex);

        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleIdException(
            IdNotFoundException ex,
            HttpServletRequest request) {

        log.error(SILENT,"""
            HTTP {} {}
            Status : {}
            Message: {}
            """,
                request.getMethod(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                ex);

        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "ID_NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(CarNotAvailableException.class)
    public ResponseEntity<ErrorResponseDto> availableHandleException(
            CarNotAvailableException ex,
            HttpServletRequest request) {

        log.error("""
            HTTP {} {}
            Status : {}
            Message: {}
            """,
                request.getMethod(),
                request.getRequestURI(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                ex);

        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "CAR_NOT_AVAILABLE",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }
}
