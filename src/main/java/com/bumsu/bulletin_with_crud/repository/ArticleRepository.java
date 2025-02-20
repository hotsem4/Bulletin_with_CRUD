package com.bumsu.bulletin_with_crud.repository;

import com.bumsu.bulletin_with_crud.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {

}