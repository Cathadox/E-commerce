package finki.dimitrij.gjorgji.lab1.handlers.errors;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class ApiError {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
