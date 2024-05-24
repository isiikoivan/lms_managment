package com.example.lms_system.language;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Language {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "language_name", nullable = true, length = -1)
    private String languageName;
    @Basic
    @Column(name = "created_on", nullable = true)
    private Time createdOn;
    @ManyToMany(mappedBy = "languages")
    private Collection<Books> books;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if (id != language.id) return false;
        if (languageName != null ? !languageName.equals(language.languageName) : language.languageName != null)
            return false;
        if (createdOn != null ? !createdOn.equals(language.createdOn) : language.createdOn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (languageName != null ? languageName.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        return result;
    }
}
