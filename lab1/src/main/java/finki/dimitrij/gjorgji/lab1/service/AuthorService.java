package finki.dimitrij.gjorgji.lab1.service;

import finki.dimitrij.gjorgji.lab1.model.dto.author.response.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorResponseDTO> getAll();

}
