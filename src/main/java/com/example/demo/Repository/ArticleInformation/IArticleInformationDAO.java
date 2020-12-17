package com.example.demo.Repository.ArticleInformation;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Supplier;

import java.util.List;

public interface IArticleInformationDAO {

    ArticleInformation saveNewArticleInformation(ArticleInformation articleInformation);

    ArticleInformation getInformationForOneArticleFromOneSupplier(Article article, Supplier supplier);

    ArticleInformation getArticleInformationById(Integer id);

    ArticleInformation getArticleInformationByArticle(Article article);

    List<ArticleInformation> getListArticleInformationForOneSupplier(Supplier supplier);

}
