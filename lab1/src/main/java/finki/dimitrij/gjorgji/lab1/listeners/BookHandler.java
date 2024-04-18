package finki.dimitrij.gjorgji.lab1.listeners;

import finki.dimitrij.gjorgji.lab1.events.BookAddedEvent;
import finki.dimitrij.gjorgji.lab1.repository.BookCategoryViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookHandler {

    private final BookCategoryViewRepository bookCategoryViewRepository;

    @EventListener
    public void onBookCreated(BookAddedEvent event) {
        log.debug("New book added [{}]", event.getSource());
        log.debug("Refreshing materialized view...");
        bookCategoryViewRepository.refreshMaterializedView();
        log.debug("Materialized view refreshed");
    }
}
