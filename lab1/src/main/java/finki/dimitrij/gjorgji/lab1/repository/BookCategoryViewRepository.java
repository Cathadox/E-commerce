package finki.dimitrij.gjorgji.lab1.repository;

import finki.dimitrij.gjorgji.lab1.model.entity.BookCategoryView;
import finki.dimitrij.gjorgji.lab1.model.enums.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryViewRepository extends JpaRepository<BookCategoryView, BookCategory> {

    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW book_category_view", nativeQuery = true)
    void refreshMaterializedView();

}
