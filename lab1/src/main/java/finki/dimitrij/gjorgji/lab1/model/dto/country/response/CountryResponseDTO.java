package finki.dimitrij.gjorgji.lab1.model.dto.country.response;

import lombok.Builder;

@Builder
public record CountryResponseDTO(Long id, String name, String continent) {
}
