package com.bumsu.bulletin_with_crud.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.bumsu.bulletin_with_crud.config.JpaConfig;
import com.bumsu.bulletin_with_crud.domain.Article;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest  // Jpa관련 집중 Test할 때 쓰임.
class JpaRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(JpaRepositoryTest.class);

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Autowired
    JpaRepositoryTest(
        ArticleRepository articleRepository,
        ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }
    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() throws Exception {
        // given

        // when
        List<Article> articles = articleRepository.findAll();
        // then
        assertThat(articles)
            .isNotNull()
            .hasSize(123);
    }
    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFind() throws Exception {
        // given
        long previousCount = articleRepository.count();
        // when
        articleRepository.save(Article.of("New Title", "New Content", "#Spring"));
        // then
        assertThat(articleRepository.count())
            .isEqualTo(previousCount + 1);
    }
    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFind() throws Exception {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "#springboot";
        article.setHashtag(updateHashtag);
        // when
        Article savedArticle = articleRepository.saveAndFlush(article);
        // then
        assertThat(savedArticle.getHashtag()).isEqualTo(updateHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() throws Exception {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentCount = article.getArticleComment().size();
        // when
        articleRepository.delete(article);

        // then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentCount);
    }
}