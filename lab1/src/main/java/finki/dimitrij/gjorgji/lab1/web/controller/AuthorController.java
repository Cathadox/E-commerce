package finki.dimitrij.gjorgji.lab1.web.controller;

import finki.dimitrij.gjorgji.lab1.model.dto.author.request.CreateAuthorDTO;
import finki.dimitrij.gjorgji.lab1.model.dto.author.response.AuthorResponseDTO;
import finki.dimitrij.gjorgji.lab1.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("")
    public ResponseEntity<AuthorResponseDTO> addAuthor(@RequestBody CreateAuthorDTO createAuthorDTO){
        final AuthorResponseDTO response = authorService.addAuthor(createAuthorDTO);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        final List<AuthorResponseDTO> response = authorService.getAll();

        return ResponseEntity.ok().body(response);
    }

}
