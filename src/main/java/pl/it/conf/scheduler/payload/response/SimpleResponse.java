package pl.it.conf.scheduler.payload.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleResponse {
    private int status;
    private String message;
}
