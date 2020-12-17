package com.example.demo.Service.ArticleOrderInformation;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleOrderInformation;
import com.example.demo.Entity.Supplier;
import com.example.demo.Repository.ArticleOrderInformation.ArticleOrderInformationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleOrderInformationService implements IArticleOrderInformationService{

    @Autowired
    ArticleOrderInformationDAO articleOrderInformationDAO;

    @Override
    public ArticleOrderInformation isInList(List<ArticleOrderInformation> articleOrderInformationList, Article article, Supplier supplier) {
        for(ArticleOrderInformation articleOrderInformation : articleOrderInformationList)
        {
            if(articleOrderInformation.getSupplier().equals(supplier) && articleOrderInformation.getArticle().equals(article))
            {
                return articleOrderInformation;
            }
        }
        return null;
    }

    @Override
    public ArticleOrderInformation newArticleOrderInformation(ArticleOrderInformation articleOrderInformation) {
        return articleOrderInformationDAO.newArticleOrderInformation(articleOrderInformation);
    }
}
