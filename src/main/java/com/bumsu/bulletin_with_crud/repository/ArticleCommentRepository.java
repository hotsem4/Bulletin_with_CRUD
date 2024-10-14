package com.bumsu.bulletin_with_crud.repository;

import com.bumsu.bulletin_with_crud.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

}
