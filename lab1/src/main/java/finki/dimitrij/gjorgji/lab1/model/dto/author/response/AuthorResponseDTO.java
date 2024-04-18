package finki.dimitrij.gjorgji.lab1.model.dto.author.response;

import finki.dimitrij.gjorgji.lab1.model.dto.country.response.CountryResponseDTO;
import lombok.Builder;

@Builder
public record AuthorResponseDTO(Long id, String name, String surname, CountryResponseDTO country) {
}
