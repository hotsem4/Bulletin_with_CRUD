package com.bumsu.bulletin_with_crud.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  // 파라미터 파싱을 위한
    @CreatedDate  // 생성 일시 자동 입력
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  //생성 일시

    @CreatedBy  // 생성자 자동 입력
    @Column(nullable = false, updatable = false ,length = 100)
    private String createdBy;  // 생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate   // 마지막 수정 일자 자동 입력
    @Column(nullable = false)
    private LocalDateTime modifiedAt;  // 수정일시

    @LastModifiedBy   // 마지막 수정자 입력
    @Column(nullable = false, length = 100)
    private String modifiedBy;  // 수정자
}
