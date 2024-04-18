package finki.dimitrij.gjorgji.lab1.web.controller;

import finki.dimitrij.gjorgji.lab1.model.dto.author.response.AuthorResponseDTO;
import finki.dimitrij.gjorgji.lab1.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        final List<AuthorResponseDTO> response = authorService.getAll();

        return ResponseEntity.ok().body(response);
    }

}
