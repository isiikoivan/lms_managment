package com.example.lms_system.books;

import com.example.lms_system.author.Author;
import com.example.lms_system.edition.Edition;
import com.example.lms_system.language.Language;
import com.example.lms_system.publisher.Publisher;
import com.example.lms_system.request.Request;
import com.example.lms_system.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Books {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "title", nullable = true, length = -1)
    private String title;
    @Basic
    @Column(name = "author_id", nullable = true)
    private Integer authorId;
    @Basic
    @Column(name = "publisher_id", nullable = true)
    private Integer publisherId;
    @Basic
    @Column(name = "publication_date", nullable = true)
    private Date publicationDate;
    @Basic
    @Column(name = "language_id", nullable = true)
    private Integer languageId;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToMany(mappedBy = "books")
    private Collection<Request> requests;
    @OneToMany(mappedBy = "book")
    private Collection<Edition> editions;
    @ManyToMany
    @JoinTable(name="book_publisher",joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Collection<Publisher> publishers;
    @ManyToMany
    @JoinTable(name="book_language",joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Collection<Language> languages;
    @ManyToMany
    @JoinTable(name="book_author",joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Collection<Author> authors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Books books = (Books) o;

        if (id != books.id) return false;
//        if (userId != books.userId) return false;
        if (title != null ? !title.equals(books.title) : books.title != null) return false;
        if (authorId != null ? !authorId.equals(books.authorId) : books.authorId != null) return false;
        if (publisherId != null ? !publisherId.equals(books.publisherId) : books.publisherId != null) return false;
//        if (editionId != null ? !editionId.equals(books.editionId) : books.editionId != null) return false;
        if (publicationDate != null ? !publicationDate.equals(books.publicationDate) : books.publicationDate != null)
            return false;
        if (languageId != null ? !languageId.equals(books.languageId) : books.languageId != null) return false;
        if (description != null ? !description.equals(books.description) : books.description != null) return false;
        if (createdAt != null ? !createdAt.equals(books.createdAt) : books.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        result = 31 * result + (publisherId != null ? publisherId.hashCode() : 0);
//        result = 31 * result + (editionId != null ? editionId.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (languageId != null ? languageId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
//        result = 31 * result + userId;
        return result;
    }
}
