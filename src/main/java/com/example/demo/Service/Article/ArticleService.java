package com.example.demo.Service.Article;

import com.example.demo.DTO.ArticleDTO;
import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Supplier;
import com.example.demo.Repository.Article.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Article getArticleById(Integer id) {
        return articleDAO.getArticleById(id);
    }

    @Override
    public Iterable<Article> getAllArticle() {
        return articleDAO.getAllArticle();
    }


    @Override
    public List<ArticleDTO> getListArticleForOneSupplier(Supplier supplier) {
        return null;
    }

    @Override
    public List<ArticleSupplierDTO> getListArticleWithCheaperSupplier() {
        Iterable<Article> articles = getAllArticle();
        List<ArticleSupplierDTO> articleSupplierDTOS = new ArrayList<>();
        for(Article article : articles)
        {
            ArticleInformation articleInformation = cheaperSupplier(article.getArticleInformations());
            if(articleInformation!=null) {
                System.out.println("ARTICLE INFORMATION ID = " + articleInformation.getId());
                articleSupplierDTOS.add(new ArticleSupplierDTO(article.getId(), articleInformation.getId(), article.getName(), articleInformation.getSupplier(),
                        articleInformation.getPrice(), articleInformation.getStock(),articleInformation.getArticle().getType()));
            }
        }
        return  articleSupplierDTOS;
    }



    private ArticleInformation cheaperSupplier(List<ArticleInformation> articleInformations)
    {
        ArticleInformation articleInformationReturned = null;
        double maxPrice = Double.MAX_VALUE;
        for(ArticleInformation articleInformation : articleInformations)
        {
            if(maxPrice>articleInformation.getPrice())
                articleInformationReturned = articleInformation;
        }
        return articleInformationReturned;
    }
}
