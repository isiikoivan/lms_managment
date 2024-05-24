package com.example.lms_system.user;

import com.example.lms_system.address.Address;
import com.example.lms_system.books.Books;
import com.example.lms_system.contact.Contact;
import com.example.lms_system.department.Department;
import com.example.lms_system.request.Request;
import com.example.lms_system.roles.Roles;
import com.example.lms_system.security.password.reset.PasswordResetToken;
import com.example.lms_system.security.verification.VerificationToken;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "sur_name", nullable = true, length = -1)
    private String surName;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department dept;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @Basic
    @Column(name = "username", nullable = true, length = -1)
    private String username;
    @Basic
    @Column(name = "password", nullable = true, length = -1)
    private String password;
    @Basic
    @Column(name = "other_names", nullable = true, length = -1)
    private String otherNames;
    @Basic
    @Column(name = "email", nullable = true, length = -1)
    private String email;
    private boolean verified;
    @OneToMany(mappedBy = "user")
    private Collection<Address> address;
    @OneToMany(mappedBy = "user")
    private Collection<Books> books;
    @OneToMany(mappedBy = "user")
    private Collection<Contact> contact;
    @OneToMany(mappedBy = "user")
    private Collection<Request> requests;
    @OneToOne(mappedBy = "usr")
    private Request request;
    @OneToOne(mappedBy = "user")
    private PasswordResetToken passwordResetToken;

    @OneToOne(mappedBy = "user")
    private VerificationToken token;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users user = (Users) o;

        if (id != user.id) return false;
//        if (roleId != user.roleId) return false;
        if (surName != null ? !surName.equals(user.surName) : user.surName != null) return false;
//        if (departmentId != null ? !departmentId.equals(user.departmentId) : user.departmentId != null) return false;
        if (createdAt != null ? !createdAt.equals(user.createdAt) : user.createdAt != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (otherNames != null ? !otherNames.equals(user.otherNames) : user.otherNames != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
//        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
//        result = 31 * result + roleId;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (otherNames != null ? otherNames.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleType()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
