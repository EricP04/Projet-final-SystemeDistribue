package com.example.demo.Repository.ArticleInformation;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Supplier;

import java.util.List;

public interface IArticleInformationDAO {

    boolean saveNewArticleInformation(ArticleInformation articleInformation);

    ArticleInformation getInformationForOneArticleFromOneSupplier(Article article, Supplier supplier);

    List<ArticleInformation> getListArticleInformationForOneSupplier(Supplier supplier);
}
