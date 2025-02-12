package com.javaProject.personalBlogging.controller;

import com.javaProject.personalBlogging.model.Article;
import com.javaProject.personalBlogging.service.ArtcileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArtcileService articleService;

    @GetMapping
    public List<Article> getAllArticles(@RequestParam(value = "tag", required = false) String tag){
        return articleService.getAllArtciles(tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article){
        return articleService.createArticle(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails){
        try{
            Article updateArticle = articleService.updateArticle(id, articleDetails);
            return ResponseEntity.ok(updateArticle);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
