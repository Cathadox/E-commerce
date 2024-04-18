package finki.dimitrij.gjorgji.lab1.web.controller;

import finki.dimitrij.gjorgji.lab1.model.dto.author.response.AuthorResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.country.response.CountryResponseDTO;
import finki.dimitrij.gjorgji.lab1.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("")
    public ResponseEntity<List<CountryResponseDTO>> getAllCountries() {
        final List<CountryResponseDTO> response = countryService.getAllCountries();

        return ResponseEntity.ok().body(response);
    }

}
