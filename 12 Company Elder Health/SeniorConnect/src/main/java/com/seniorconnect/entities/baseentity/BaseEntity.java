package com.seniorconnect.entities.baseentity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy"},
        allowGetters = true
)
public abstract class BaseEntity {

    //@CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    //@LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate = LocalDateTime.now();
    
    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
}
