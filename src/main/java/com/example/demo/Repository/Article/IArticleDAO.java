package com.example.demo.Repository.Article;

import com.example.demo.Entity.Article;

import java.util.List;

public interface IArticleDAO {

    Article saveNewArticle(Article article);

    Article getArticleByName(String name);

    Article getArticleById(Integer id);

    Iterable<Article> getAllArticle();



}
