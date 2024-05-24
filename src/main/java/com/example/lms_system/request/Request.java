package com.example.lms_system.request;

import com.example.lms_system.books.Books;
import com.example.lms_system.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "request_type", nullable = true, length = -1)
    private String requestType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @Basic
    @Column(name = "request_date", nullable = true)
    private Date requestDate;
    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Basic
    @Column(name = "approval_date", nullable = true)
    private Date approvalDate;
    @OneToOne
    @JoinColumn(name = "approved_by")
    private Users usr;
    @ManyToMany
    @JoinTable(name = "request_book",joinColumns = @JoinColumn(name = "request_id"),inverseJoinColumns = @JoinColumn(name = "book-id"))
    private Collection<Books> books;
    @Basic
    @Column(name = "return_date", nullable = true)
    private Time returnDate;
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

//        if (userId != request.userId) return false;
//        if (bookId != request.bookId) return false;
        if (id != request.id) return false;
        if (requestType != null ? !requestType.equals(request.requestType) : request.requestType != null) return false;
        if (requestDate != null ? !requestDate.equals(request.requestDate) : request.requestDate != null) return false;
        if (approvalDate != null ? !approvalDate.equals(request.approvalDate) : request.approvalDate != null)
            return false;
//        if (approvedBy != null ? !approvedBy.equals(request.approvedBy) : request.approvedBy != null) return false;
        if (returnDate != null ? !returnDate.equals(request.returnDate) : request.returnDate != null) return false;
        if (status != null ? !status.equals(request.status) : request.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = requestType != null ? requestType.hashCode() : 0;
//        result = 31 * result + userId;
        result = 31 * result + (requestDate != null ? requestDate.hashCode() : 0);
        result = 31 * result + (approvalDate != null ? approvalDate.hashCode() : 0);
//        result = 31 * result + (approvedBy != null ? approvedBy.hashCode() : 0);
//        result = 31 * result + bookId;
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + id.intValue();
        return result;
    }
}
