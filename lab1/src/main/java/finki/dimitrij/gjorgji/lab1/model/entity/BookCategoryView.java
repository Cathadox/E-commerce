package finki.dimitrij.gjorgji.lab1.model.entity;

import finki.dimitrij.gjorgji.lab1.model.enums.BookCategory;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Table(name = "book_category_view")
@Subselect(value = "SELECT * from book_category_view")
@Immutable
public class BookCategoryView {

    @Column(name = "count")
    private Integer count;

    @Id
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private BookCategory category;

}
