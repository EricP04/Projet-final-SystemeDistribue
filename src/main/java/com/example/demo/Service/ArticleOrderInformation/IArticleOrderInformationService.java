package com.example.demo.Service.ArticleOrderInformation;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleOrderInformation;
import com.example.demo.Entity.Supplier;
import com.example.demo.Repository.ArticleInformation.IArticleInformationDAO;
import com.example.demo.Repository.ArticleOrderInformation.IArticleOrderInformationDAO;

import java.util.List;

public interface IArticleOrderInformationService extends IArticleOrderInformationDAO {
    ArticleOrderInformation isInList(List<ArticleOrderInformation> articleOrderInformationList, Article article, Supplier supplier);
}
