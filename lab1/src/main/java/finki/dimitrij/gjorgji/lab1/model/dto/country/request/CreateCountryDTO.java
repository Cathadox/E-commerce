package finki.dimitrij.gjorgji.lab1.model.dto.country.request;

import lombok.Builder;

@Builder
public record CreateCountryDTO(String name, String continent) {
}
