package com.example.demo.Service.Article;

import com.example.demo.DTO.ArticleDTO;
import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Supplier;
import com.example.demo.Repository.Article.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticleService{

    @Autowired
    private ArticleDAO articleDAO;


    @Override
    public Article saveNewArticle(Article article) {
        return articleDAO.saveNewArticle(article);
    }

    @Override
    public Article getArticleByName(String name) {
        return articleDAO.getArticleByName(name);
    }


    @Override
    public List<ArticleDTO> getListArticleForOneSupplier(Supplier supplier) {
        return null;
    }
}
