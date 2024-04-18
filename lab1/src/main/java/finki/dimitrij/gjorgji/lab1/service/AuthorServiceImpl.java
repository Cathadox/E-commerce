package finki.dimitrij.gjorgji.lab1.service;

import finki.dimitrij.gjorgji.lab1.mapper.DomainMapper;
import finki.dimitrij.gjorgji.lab1.model.dto.author.response.AuthorResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.entity.Author;
import finki.dimitrij.gjorgji.lab1.model.entity.Book;
import finki.dimitrij.gjorgji.lab1.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final DomainMapper domainMapper;

    @Override
    public List<AuthorResponseDTO> getAll() {
        log.debug("Getting all books");
        final List<Author> authors = authorRepository.findAll();

        return domainMapper.authorsToAuthorResponseDTOs(authors);
    }
}
