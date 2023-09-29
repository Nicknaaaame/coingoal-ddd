package ru.lapotko.coingoal.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private List<String> errors;
    private HttpStatus status;
}
