package com.bumsu.bulletin_with_crud.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@ToString
@Getter
@Table(name = "Article", indexes = {
    @Index(columnList = "title"),
    @Index(columnList = "hashtag"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // mysql 방식으로 세팅
    private Long id;

    @Setter @Column(nullable = false)
    private String title;  // 제목

    @Setter @Column(nullable = false, length = 1000)
    private String content;  // 본문

    @Setter private String hashtag;  // 해시태그

    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<ArticleComment> articleComment = new LinkedHashSet<>();

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

    protected Article() {  // 하이버네이트 기준 모든 Jpa Entity는 기본 생성자를 갖고 있어야 한다.
    } // 평소 오픈할 필요가 없기 때문에 protected로 하자.

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
    // 정적 팩토리 메서드
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content,hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article article = (Article) o;
        // 막 만들어서 아직 영속화되지 않은 엔티티는 모두 동등성 검사에서 탈락한다.
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
