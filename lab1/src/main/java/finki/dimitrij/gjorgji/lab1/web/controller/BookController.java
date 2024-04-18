package finki.dimitrij.gjorgji.lab1.web.controller;

import finki.dimitrij.gjorgji.lab1.model.dto.book.request.CreateBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.request.EditBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.response.BookResponseDTO;
import finki.dimitrij.gjorgji.lab1.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody CreateBookDTO createBookDTO) {
        final BookResponseDTO response = bookService.addBook(createBookDTO);

        return ResponseEntity.created(URI.create("/books")).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> editBook(@PathVariable Long id, @RequestBody EditBookDTO editBookDTO) {
        final BookResponseDTO response = bookService.editBook(id, editBookDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<BookResponseDTO> rentBook(@PathVariable Long id) {
        final BookResponseDTO response = bookService.markAsRented(id);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Long id) {
        final BookResponseDTO response = bookService.getBook(id);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<BookResponseDTO>> getAllBooks(Pageable pageable){
        final Page<BookResponseDTO> response = bookService.getAllBooks(pageable);

        return ResponseEntity.ok().body(response);
    }

}
