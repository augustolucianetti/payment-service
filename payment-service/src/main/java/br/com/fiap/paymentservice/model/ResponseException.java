package br.com.fiap.paymentservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResponseException extends Exception{

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private HttpStatus status;

    public ResponseException(LocalDateTime timestamp, String message, HttpStatus http) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = http;
    }
}
