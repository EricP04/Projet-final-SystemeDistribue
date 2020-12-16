package com.example.demo.Service.ArticleInfomation;

import com.example.demo.DTO.ArticleDTO;
import com.example.demo.Entity.Supplier;
import com.example.demo.Repository.ArticleInformation.IArticleInformationDAO;

import java.util.List;

public interface IArticleInformationService extends IArticleInformationDAO {
    List<ArticleDTO> getListArticleDTOFromOneSupplier(Supplier supplier);
}
