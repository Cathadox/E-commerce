package finki.dimitrij.gjorgji.lab1.service;

import finki.dimitrij.gjorgji.lab1.model.dto.book.request.CreateBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.request.EditBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.response.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO addBook(CreateBookDTO createBookDTO);

    void deleteBook(Long id);

    BookResponseDTO editBook(EditBookDTO editBookDTO);

    BookResponseDTO markAsRented(Long id);

    BookResponseDTO getBook(Long id);

    List<BookResponseDTO> getAllBooks();

}
