package finki.dimitrij.gjorgji.lab1.model.dto.book.response;

import finki.dimitrij.gjorgji.lab1.model.dto.author.response.AuthorResponseDTO;
import lombok.Builder;

@Builder
public record BookResponseDTO(Long id, String name, String category, AuthorResponseDTO author,
                              Integer availableCopies) {
}
