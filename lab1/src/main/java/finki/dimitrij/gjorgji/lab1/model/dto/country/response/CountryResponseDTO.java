package finki.dimitrij.gjorgji.lab1.model.dto.country.response;

import lombok.Builder;

@Builder
public record CountryResponseDTO(String name, String continent) {
}
