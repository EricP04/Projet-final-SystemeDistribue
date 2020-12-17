package com.example.demo.Repository.ArticleInformation;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleInformationDAO implements IArticleInformationDAO {

    @Autowired
    private IArticleInformationRepository articleInformationRepository;


    @Override
    public ArticleInformation saveNewArticleInformation(ArticleInformation articleInformation) {
        return articleInformationRepository.save(articleInformation);
    }

    @Override
    public ArticleInformation getInformationForOneArticleFromOneSupplier(Article article, Supplier supplier) {
        Iterable<ArticleInformation> articleInformations = articleInformationRepository.findAll();
        for(ArticleInformation articleInformation : articleInformations)
        {
            if(articleInformation.getSupplier().equals(supplier) & articleInformation.getArticle().equals(article))
                return articleInformation;

        }
        return null;
    }

    @Override
    public ArticleInformation getArticleInformationById(Integer id) {
        Iterable<ArticleInformation> articleInformations = articleInformationRepository.findAll();
        for(ArticleInformation articleInformation : articleInformations)
        {
            System.out.println("ArticleInfoId:"+articleInformation.getId());
            System.out.println("Compare with id =" + id);
            if(articleInformation.getId()==id)
                return articleInformation;
        }
        return null;
    }

    @Override
    public ArticleInformation getArticleInformationByArticle(Article article) {
        Iterable<ArticleInformation> articleInformations = articleInformationRepository.findAll();
        for(ArticleInformation articleInformation : articleInformations)
        {
            if(articleInformation.getArticle().equals(article))
                return articleInformation;
        }
        return null;
    }

    @Override
    public List<ArticleInformation> getListArticleInformationForOneSupplier(Supplier supplier) {
        Iterable<ArticleInformation> articleInformations = articleInformationRepository.findAll();
        List<ArticleInformation> listArticleInformation = new ArrayList<>();
        for(ArticleInformation articleInformation : articleInformations)
        {
            if(articleInformation.getSupplier().equals(supplier))
                listArticleInformation.add(articleInformation);
        }
        return listArticleInformation;
    }


}
