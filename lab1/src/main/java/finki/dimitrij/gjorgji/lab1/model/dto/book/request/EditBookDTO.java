package finki.dimitrij.gjorgji.lab1.model.dto.book.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record EditBookDTO(
        @NotNull
        Long id,
        String name, String category, Long authorId, Integer availableCopies
) {
}
