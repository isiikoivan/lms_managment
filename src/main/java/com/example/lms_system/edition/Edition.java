package com.example.lms_system.edition;

import com.example.lms_system.books.Books;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Edition {
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;
    @Basic
    @Column(name = "isbn", nullable = false)
    private int isbn;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edition edition = (Edition) o;

        if (isbn != edition.isbn) return false;
        if (id != edition.id) return false;
//        if (bookId != null ? !bookId.equals(edition.bookId) : edition.bookId != null) return false;
//        if (bookEdition != null ? !bookEdition.equals(edition.bookEdition) : edition.bookEdition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
//        result = 31 * result + (bookEdition != null ? bookEdition.hashCode() : 0);
        result = 31 * result + isbn;
//        result = 31 * result + id;
        return result;
    }
}
