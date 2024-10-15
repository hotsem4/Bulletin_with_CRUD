package com.bumsu.bulletin_with_crud.repository;

import com.bumsu.bulletin_with_crud.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource  // Spring Data Rest에서 사용되는 어노테이션
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

}
