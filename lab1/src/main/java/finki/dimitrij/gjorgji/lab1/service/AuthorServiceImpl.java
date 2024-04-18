package finki.dimitrij.gjorgji.lab1.service;

import finki.dimitrij.gjorgji.lab1.events.BookAddedEvent;
import finki.dimitrij.gjorgji.lab1.mapper.DomainMapper;
import finki.dimitrij.gjorgji.lab1.model.dto.author.request.CreateAuthorDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.author.response.AuthorResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.response.BookResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.entity.Author;
import finki.dimitrij.gjorgji.lab1.model.entity.Book;
import finki.dimitrij.gjorgji.lab1.model.entity.Country;
import finki.dimitrij.gjorgji.lab1.repository.AuthorRepository;
import finki.dimitrij.gjorgji.lab1.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final DomainMapper domainMapper;

    @Override
    public List<AuthorResponseDTO> getAll() {
        log.debug("Getting all books");
        final List<Author> authors = authorRepository.findAll();

        return domainMapper.authorsToAuthorResponseDTOs(authors);
    }

    @Override
    public AuthorResponseDTO addAuthor(CreateAuthorDTO createAuthorDTO) {
        final Author author = domainMapper.createAuthorDTOtoAuthor(createAuthorDTO);

        Country country;
        if (createAuthorDTO.country() != null) {
            country = countryRepository.findById(createAuthorDTO.country()).orElseThrow();
            author.setCountry(country);
        }

        authorRepository.save(author);
        log.debug("Saving author: [{}]", author);

        return domainMapper.authorToAuthorResponseDTO(author);
    }
}
