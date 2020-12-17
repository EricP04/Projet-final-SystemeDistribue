package com.example.demo.Repository.ArticleOrderInformation;

import com.example.demo.Entity.ArticleOrderInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleOrderInformationDAO implements IArticleOrderInformationDAO{

    @Autowired
    ArticleOrderInformationRepository articleOrderInformationRepository;

    @Override
    public ArticleOrderInformation newArticleOrderInformation(ArticleOrderInformation articleOrderInformation) {
        return articleOrderInformationRepository.save(articleOrderInformation);
    }
}
