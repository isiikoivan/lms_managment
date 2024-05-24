package com.example.lms_system.contact;

import com.example.lms_system.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    @Basic
    @Column(name = "contact_no", nullable = true, length = -1)
    private String contactNo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (contactNo != null ? !contactNo.equals(contact.contactNo) : contact.contactNo != null) return false;
//        if (userId != null ? !userId.equals(contact.userId) : contact.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contactNo != null ? contactNo.hashCode() : 0;
//        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
