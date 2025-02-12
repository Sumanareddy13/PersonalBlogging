package com.javaProject.personalBlogging.repository;

import com.javaProject.personalBlogging.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTagsContaining(String tag);
}
