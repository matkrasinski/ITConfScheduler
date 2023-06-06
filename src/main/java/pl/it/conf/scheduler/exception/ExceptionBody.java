package pl.it.conf.scheduler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionBody {
    private int status;
    private String message;
    private String error;
}
