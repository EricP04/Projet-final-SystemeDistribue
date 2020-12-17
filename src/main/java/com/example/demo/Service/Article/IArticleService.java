package com.example.demo.Service.Article;

import com.example.demo.DTO.ArticleDTO;
import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Supplier;
import com.example.demo.Repository.Article.IArticleDAO;

import java.util.List;

public interface IArticleService extends IArticleDAO {

    List<ArticleDTO> getListArticleForOneSupplier(Supplier supplier);
    List<ArticleSupplierDTO> getListArticleWithCheaperSupplier();
}
