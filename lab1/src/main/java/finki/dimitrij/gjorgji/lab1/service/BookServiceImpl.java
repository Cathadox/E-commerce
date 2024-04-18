package finki.dimitrij.gjorgji.lab1.service;

import finki.dimitrij.gjorgji.lab1.events.BookAddedEvent;
import finki.dimitrij.gjorgji.lab1.exceptions.NotNullException;
import finki.dimitrij.gjorgji.lab1.exceptions.ResourceCannotBeModifiedException;
import finki.dimitrij.gjorgji.lab1.exceptions.ResourceNotFoundException;
import finki.dimitrij.gjorgji.lab1.mapper.DomainMapper;
import finki.dimitrij.gjorgji.lab1.model.dto.book.request.CreateBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.request.EditBookDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.book.response.BookResponseDTO;
import finki.dimitrij.gjorgji.lab1.model.entity.Author;
import finki.dimitrij.gjorgji.lab1.model.entity.Book;
import finki.dimitrij.gjorgji.lab1.model.enums.BookCategory;
import finki.dimitrij.gjorgji.lab1.repository.AuthorRepository;
import finki.dimitrij.gjorgji.lab1.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final DomainMapper domainMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    @Override
    public BookResponseDTO addBook(CreateBookDTO createBookDTO) {
        final Book book = domainMapper.createBookDTOtoBook(createBookDTO);

        Author author;
        if (createBookDTO.authorId() != null) {
            author = authorRepository.findById(createBookDTO.authorId()).orElseThrow();
            book.setAuthor(author);
        }
        bookRepository.save(book);
        log.debug("Saving book: [{}]", book);

        final BookResponseDTO response = domainMapper.bookToBookResponseDTO(book);
        applicationEventPublisher.publishEvent(new BookAddedEvent(response));
        return response;
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        log.debug("Deleting book with id: [{}]", id);
        try {
            bookRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new NotNullException("id");
        }
    }

    @Transactional
    @Override
    public BookResponseDTO editBook(Long id, EditBookDTO editBookDTO) {
        log.debug("Updating book [{}]", editBookDTO);
        try {
            final Book book = bookRepository.findById(id).map(existingBook -> {
                if (editBookDTO.name() != null) existingBook.setName(editBookDTO.name());
                if (editBookDTO.category() != null)
                    existingBook.setCategory(BookCategory.valueOf(editBookDTO.category()));
                if (editBookDTO.authorId() != null) {
                    final Author author = authorRepository.findById(editBookDTO.authorId()).orElseThrow();
                    existingBook.setAuthor(author);
                }
                if (editBookDTO.availableCopies() != null && editBookDTO.availableCopies() >= 0)
                    existingBook.setAvailableCopies(editBookDTO.availableCopies());
                return bookRepository.save(existingBook);
            }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

            return domainMapper.bookToBookResponseDTO(book);
        } catch (IllegalArgumentException e) {
            throw new NotNullException("id");
        }
    }

    @Transactional
    @Override
    public BookResponseDTO markAsRented(Long id) {
        final Book book = bookRepository.findById(id).map(bookToRent -> {
            Integer availableCopies = bookToRent.getAvailableCopies();
            if (availableCopies > 0) {
                log.debug("Renting a copy of book: [{}]", bookToRent);
                availableCopies--;
                bookToRent.setAvailableCopies(availableCopies);
                return bookRepository.save(bookToRent);
            }
            String errorMessage = String.format("No more copies to rent of this book: id [%s], available copies is 0", bookToRent.getId());
            log.debug(errorMessage);
            throw new ResourceCannotBeModifiedException(errorMessage);
        }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return domainMapper.bookToBookResponseDTO(book);
    }

    @Override
    public BookResponseDTO getBook(Long id) {
        log.debug("Getting book with id: [{}]", id);
        final Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return domainMapper.bookToBookResponseDTO(book);
    }

    @Override
    public Page<BookResponseDTO> getAllBooks(Pageable pageable) {
        log.debug("Getting all books");
        final Page<Book> books = bookRepository.findAll(pageable);

        return domainMapper.toDto(books);
    }
}
