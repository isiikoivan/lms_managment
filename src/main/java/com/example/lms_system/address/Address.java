package com.example.lms_system.address;

import com.example.lms_system.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Basic
    @Column(name = "district", nullable = true, length = -1)
    private String district;
    @Basic
    @Column(name = "created_on", nullable = true)
    private Timestamp createdOn;
    @Basic
    @Column(name = "county", nullable = true, length = -1)
    private String county;
    @Basic
    @Column(name = "sub_county", nullable = true, length = -1)
    private String subCounty;
    @Basic
    @Column(name = "parish", nullable = true, length = -1)
    private String parish;
    @Basic
    @Column(name = "village", nullable = true, length = -1)
    private String village;
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

        Address address = (Address) o;

//        if (userId != address.userId) return false;
        if (district != null ? !district.equals(address.district) : address.district != null) return false;
        if (createdOn != null ? !createdOn.equals(address.createdOn) : address.createdOn != null) return false;
        if (county != null ? !county.equals(address.county) : address.county != null) return false;
        if (subCounty != null ? !subCounty.equals(address.subCounty) : address.subCounty != null) return false;
        if (parish != null ? !parish.equals(address.parish) : address.parish != null) return false;
        if (village != null ? !village.equals(address.village) : address.village != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = district != null ? district.hashCode() : 0;
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (subCounty != null ? subCounty.hashCode() : 0);
        result = 31 * result + (parish != null ? parish.hashCode() : 0);
        result = 31 * result + (village != null ? village.hashCode() : 0);
//        result = 31 * result + userId;
        return result;
    }
}
