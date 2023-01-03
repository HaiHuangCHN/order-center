package com.nice.order.center.dao.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * No Lombok annotation
 */
@MappedSuperclass
public class BaseEntity {


    /**
     * Created by who
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * Record created date and time
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Updated by who
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * Record last updated date and time
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


}
