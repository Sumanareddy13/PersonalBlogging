package com.javaProject.personalBlogging.service;

import com.javaProject.personalBlogging.model.Article;
import com.javaProject.personalBlogging.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtcileService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArtciles(String tag){
        if(tag != null){
            return articleRepository.findByTagsContaining(tag);
        }
        return articleRepository.findAll();
    }
    public Optional<Article> getArticleById(Long id){
        return articleRepository.findById(id);
    }
    public Article createArticle(Article article){
        return articleRepository.save(article);
    }
    public Article updateArticle(Long id, Article articleDetails){
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());
        article.setTags(articleDetails.getTags());
        article.setPublishedDate(articleDetails.getPublishedDate());
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id){
        articleRepository.deleteById(id);
    }
}
