package finki.dimitrij.gjorgji.lab1.repository;

import finki.dimitrij.gjorgji.lab1.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
