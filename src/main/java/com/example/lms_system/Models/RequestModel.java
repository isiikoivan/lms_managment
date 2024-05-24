package com.example.lms_system.Models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "request", schema = "public", catalog = "lms_db")
public class RequestModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "request_type")
    private String requestType;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "request_date")
    private Date requestDate;
    @Basic
    @Column(name = "fullfillment_date")
    private Date fullfillmentDate;
    @Basic
    @Column(name = "is_fullfilled")
    private Boolean isFullfilled;
    @Basic
    @Column(name = "approved_by")
    private Integer approvedBy;
    @Basic
    @Column(name = "book_id")
    private Integer bookId;
    @Basic
    @Column(name = "created_at")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getFullfillmentDate() {
        return fullfillmentDate;
    }

    public void setFullfillmentDate(Date fullfillmentDate) {
        this.fullfillmentDate = fullfillmentDate;
    }

    public Boolean getFullfilled() {
        return isFullfilled;
    }

    public void setFullfilled(Boolean fullfilled) {
        isFullfilled = fullfilled;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestModel that = (RequestModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (requestType != null ? !requestType.equals(that.requestType) : that.requestType != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (requestDate != null ? !requestDate.equals(that.requestDate) : that.requestDate != null) return false;
        if (fullfillmentDate != null ? !fullfillmentDate.equals(that.fullfillmentDate) : that.fullfillmentDate != null)
            return false;
        if (isFullfilled != null ? !isFullfilled.equals(that.isFullfilled) : that.isFullfilled != null) return false;
        if (approvedBy != null ? !approvedBy.equals(that.approvedBy) : that.approvedBy != null) return false;
        if (bookId != null ? !bookId.equals(that.bookId) : that.bookId != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (requestType != null ? requestType.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (requestDate != null ? requestDate.hashCode() : 0);
        result = 31 * result + (fullfillmentDate != null ? fullfillmentDate.hashCode() : 0);
        result = 31 * result + (isFullfilled != null ? isFullfilled.hashCode() : 0);
        result = 31 * result + (approvedBy != null ? approvedBy.hashCode() : 0);
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
