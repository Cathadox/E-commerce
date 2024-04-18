package finki.dimitrij.gjorgji.lab1.model.dto.book.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record EditBookDTO(
        String name, String category, Long authorId, Integer availableCopies
) {
}
