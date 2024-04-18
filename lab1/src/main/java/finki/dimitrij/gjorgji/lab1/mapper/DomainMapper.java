package finki.dimitrij.gjorgji.lab1.mapper;

import finki.dimitrij.gjorgji.lab1.model.dto.author.request.CreateAuthorDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.author.response.AuthorResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.request.CreateBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.response.BookResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.country.response.CountryResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.entity.Author;
import finki.dimitrij.gjorgji.lab1.model.entity.Book;
import finki.dimitrij.gjorgji.lab1.model.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DomainMapper {

    @Mapping(source = "authorId", target = "author", ignore = true)
    Book createBookDTOtoBook(CreateBookDTO createBookDTO);

    default Page<BookResponseDTO> toDto(Page<Book> page) {
        return page.map(this::bookToBookResponseDTO);
    }

    BookResponseDTO bookToBookResponseDTO(Book book);

    AuthorResponseDTO authorToAuthorResponseDTO(Author author);

    CountryResponseDTO countryToCountryResponseDTO(Country country);

    List<CountryResponseDTO> countriesToCountryResponseDTOs(List<Country> countries);

    List<AuthorResponseDTO> authorsToAuthorResponseDTOs(List<Author> authors);

    @Mapping(source = "country", target = "country", ignore = true)
    Author createAuthorDTOtoAuthor(CreateAuthorDTO createAuthorDTO);
}
