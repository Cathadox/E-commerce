package finki.dimitrij.gjorgji.lab1.events;

import finki.dimitrij.gjorgji.lab1.model.dto.book.response.BookResponseDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class BookAddedEvent extends ApplicationEvent {

    private final LocalDateTime time = LocalDateTime.now();

    public BookAddedEvent(BookResponseDTO source) {
        super(source);
    }

}
