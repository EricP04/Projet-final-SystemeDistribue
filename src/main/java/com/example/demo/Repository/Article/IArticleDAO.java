package com.example.demo.Repository.Article;

import com.example.demo.Entity.Article;

public interface IArticleDAO {

    boolean saveNewArticle(Article article);

    Article getArticleByName(String name);



}
