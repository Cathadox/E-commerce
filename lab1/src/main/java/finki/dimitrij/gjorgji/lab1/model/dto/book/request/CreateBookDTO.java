package finki.dimitrij.gjorgji.lab1.model.dto.book.request;

import lombok.Builder;

@Builder
public record CreateBookDTO(String name, String category, Long authorId, Integer availableCopies) {
}
