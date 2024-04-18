package finki.dimitrij.gjorgji.lab1.service;

import finki.dimitrij.gjorgji.lab1.mapper.DomainMapper;
import finki.dimitrij.gjorgji.lab1.model.dto.country.response.CountryResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.entity.Author;
import finki.dimitrij.gjorgji.lab1.model.entity.Country;
import finki.dimitrij.gjorgji.lab1.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final DomainMapper domainMapper;

    @Override
    public List<CountryResponseDTO> getAllCountries() {
        log.debug("Getting all books");
        final List<Country> countries = countryRepository.findAll();

        return domainMapper.countriesToCountryResponseDTOs(countries);
    }
}
