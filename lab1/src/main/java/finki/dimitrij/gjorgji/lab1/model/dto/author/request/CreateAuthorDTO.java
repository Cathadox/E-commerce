package finki.dimitrij.gjorgji.lab1.model.dto.author.request;

import lombok.Builder;

@Builder
public record CreateAuthorDTO(String name, String surname, Long country) {
}
