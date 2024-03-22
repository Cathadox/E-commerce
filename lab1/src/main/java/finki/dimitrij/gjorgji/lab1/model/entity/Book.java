package finki.dimitrij.gjorgji.lab1.model.entity;

import finki.dimitrij.gjorgji.lab1.model.enums.BookCategory;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    private int version;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private BookCategory category;

    @ManyToOne
    private Author author;

    @Column(name = "available_copies")
    private Integer availableCopies;

}
