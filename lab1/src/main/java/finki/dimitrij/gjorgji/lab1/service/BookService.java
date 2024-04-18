package finki.dimitrij.gjorgji.lab1.service;

import finki.dimitrij.gjorgji.lab1.model.dto.book.request.CreateBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.request.EditBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.response.BookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookResponseDTO addBook(CreateBookDTO createBookDTO);

    void deleteBook(Long id);

    BookResponseDTO editBook(Long id, EditBookDTO editBookDTO);

    BookResponseDTO markAsRented(Long id);

    BookResponseDTO getBook(Long id);

    Page<BookResponseDTO> getAllBooks(Pageable pageable);

}
