package com.example.demo.Service.ArticleInfomation;

import com.example.demo.DTO.ArticleDTO;
import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Supplier;
import com.example.demo.Repository.ArticleInformation.ArticleInformationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleInformationService implements IArticleInformationService {

    @Autowired
    private ArticleInformationDAO articleInformationDAO;

    @Override
    public ArticleInformation saveNewArticleInformation(ArticleInformation articleInformation) {
        return articleInformationDAO.saveNewArticleInformation(articleInformation);
    }

    @Override
    public ArticleInformation getInformationForOneArticleFromOneSupplier(Article article, Supplier supplier) {
        return articleInformationDAO.getInformationForOneArticleFromOneSupplier(article,supplier);
    }

    @Override
    public ArticleInformation getArticleInformationById(Integer id) {
        return articleInformationDAO.getArticleInformationById(id);
    }

    @Override
    public ArticleInformation getArticleInformationByArticle(Article article) {
        return articleInformationDAO.getArticleInformationByArticle(article);
    }

    @Override
    public List<ArticleInformation> getListArticleInformationForOneSupplier(Supplier supplier) {
        List<ArticleInformation> articleInformations = articleInformationDAO.getListArticleInformationForOneSupplier(supplier);
        return articleInformations;

    }

    @Override
    public List<ArticleDTO> getListArticleDTOFromOneSupplier(Supplier supplier) {
        List<ArticleInformation> articleInformations = getListArticleInformationForOneSupplier(supplier);
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (ArticleInformation article: articleInformations
             ) {
            articleDTOS.add(new ArticleDTO(article.getArticle().getName(),article.getPrice(),article.getStock()));
        }
        return articleDTOS;
    }
}
