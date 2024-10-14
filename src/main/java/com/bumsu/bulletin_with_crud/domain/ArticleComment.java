package com.bumsu.bulletin_with_crud.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


@ToString
@Getter
@Table(name = "ArticleComment", indexes = {  // 검색의 용이성을 위해 index로 만들 것들
    @Index(columnList = "content"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@EntityListeners(EntityListeners.class)
@Entity
public class ArticleComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private Article article;  // 게시글 (ID)
    @Setter @Column(nullable = false, length = 500)
    private String content;  // 본문

    @CreatedDate  // 생성 일시 자동 입력
    @Column(nullable = false)
    private LocalDateTime createdAt;  //생성 일시
    @CreatedBy  // 생성자 자동 입력
    @Column(nullable = false, length = 100)
    private String createdBy;  // 생성자
    @LastModifiedDate   // 마지막 수정 일자 자동 입력
    @Column(nullable = false)
    private LocalDateTime modifiedAt;  // 수정일시
    @LastModifiedBy   // 마지막 수정자 입력
    @Column(nullable = false, length = 100)
    private String modifiedBy;  // 수정자

    protected ArticleComment() {
    }

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }
    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArticleComment that = (ArticleComment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
