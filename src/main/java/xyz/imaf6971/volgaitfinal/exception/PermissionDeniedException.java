package xyz.imaf6971.volgaitfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException() {

    }

    public PermissionDeniedException(String message) {
        super(message);
    }

}
