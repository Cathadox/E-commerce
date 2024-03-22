package finki.dimitrij.gjorgji.lab1.mapper;

import finki.dimitrij.gjorgji.lab1.model.dto.author.response.AuthorResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.request.CreateBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.response.BookResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.country.response.CountryResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.entity.Author;
import finki.dimitrij.gjorgji.lab1.model.entity.Book;
import finki.dimitrij.gjorgji.lab1.model.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DomainMapper {

    @Mapping(source = "authorId", target = "author", ignore = true)
    Book createBookDTOtoBook(CreateBookDTO createBookDTO);

    List<BookResponseDTO> booksToBookResponseDTOs(List<Book> books);

    BookResponseDTO bookToBookResponseDTO(Book book);

    AuthorResponseDTO authorToAuthorResponseDTO(Author author);

    CountryResponseDTO countryToCountryResponseDTO(Country country);

}
