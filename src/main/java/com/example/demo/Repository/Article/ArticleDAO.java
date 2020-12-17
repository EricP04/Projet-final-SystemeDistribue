package com.example.demo.Repository.Article;

import com.example.demo.Entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDAO implements IArticleDAO{

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public Article saveNewArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article getArticleByName(String name) {
        Iterable<Article> articles = articleRepository.findAll();
        for(Article article: articles)
        {
            if(article.getName().toUpperCase().compareTo(name.toUpperCase())==0)
                return article;
        }
        return null;
    }

    @Override
    public Article getArticleById(Integer id) {
        Iterable<Article> articles= articleRepository.findAll();
        for(Article article : articles)
        {
            if(article.getId()==id)
                return article;
        }
        return null;
    }

    @Override
    public Iterable<Article> getAllArticle() {
        return articleRepository.findAll();
    }
}
