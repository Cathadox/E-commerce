package finki.dimitrij.gjorgji.lab1.listeners;

import finki.dimitrij.gjorgji.lab1.events.BookAddedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookHandler {

    @EventListener
    public void onBookCreated(BookAddedEvent event) {
        log.debug("New book added [{}]", event.getSource());
    }
}
