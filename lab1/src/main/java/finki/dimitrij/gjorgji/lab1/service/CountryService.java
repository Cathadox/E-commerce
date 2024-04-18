package finki.dimitrij.gjorgji.lab1.service;

import finki.dimitrij.gjorgji.lab1.model.dto.country.response.CountryResponseDTO;

import java.util.List;

public interface CountryService {

    List<CountryResponseDTO> getAllCountries();

}
