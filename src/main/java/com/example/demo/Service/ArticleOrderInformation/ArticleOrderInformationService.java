package com.example.demo.Service.ArticleOrderInformation;

import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.ArticleOrderInformation;
import com.example.demo.Entity.Supplier;
import com.example.demo.Repository.ArticleOrderInformation.ArticleOrderInformationDAO;
import com.example.demo.Service.ArticleInfomation.ArticleInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleOrderInformationService implements IArticleOrderInformationService{

    @Autowired
    ArticleOrderInformationDAO articleOrderInformationDAO;

    @Autowired
    ArticleInformationService articleInformationService;

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
    public List<ArticleSupplierDTO> convertToListArticleSupplierDTO(List<ArticleOrderInformation> articleOrderInformations) {
        List<ArticleSupplierDTO> articleSupplierDTOS = new ArrayList<>();
        for(ArticleOrderInformation article : articleOrderInformations)
        {

            ArticleInformation articleInformation = articleInformationService.getInformationForOneArticleFromOneSupplier(article.getArticle(),article.getSupplier());
            if(articleInformation!=null)
                articleSupplierDTOS.add(new ArticleSupplierDTO(article.getArticle().getId(),articleInformation.getId(),
                        article.getArticle().getName(),article.getSupplier(),article.getPrice(),article.getCount(),article.getArticle().getType()));
        }
        return articleSupplierDTOS;
    }

    @Override
    public ArticleOrderInformation newArticleOrderInformation(ArticleOrderInformation articleOrderInformation) {
        return articleOrderInformationDAO.newArticleOrderInformation(articleOrderInformation);
    }
}
