package com.example.lms_system.author;

import com.example.lms_system.books.Books;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "author_name", nullable = true, length = -1)
    private String authorName;
    @Basic
    @Column(name = "created_on", nullable = true)
    private Time createdOn;
    @ManyToMany(mappedBy = "authors")
    private Collection<Books> books;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (id != author.id) return false;
        if (authorName != null ? !authorName.equals(author.authorName) : author.authorName != null) return false;
        if (createdOn != null ? !createdOn.equals(author.createdOn) : author.createdOn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        return result;
    }
}
