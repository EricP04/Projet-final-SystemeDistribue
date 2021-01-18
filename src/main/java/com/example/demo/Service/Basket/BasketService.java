package com.example.demo.Service.Basket;

import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.Entity.Basket;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.ArticleInformation.ArticleInformationDAO;
import com.example.demo.Repository.Basket.BasketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService implements IBasketService {

    @Autowired
    private BasketDAO basketDAO;

    @Autowired
    private ArticleInformationDAO articleInformationDAO;

    @Override
    public List<ArticleSupplierDTO> getListArticleBasketForCustomer(Customer customer) {
        return basketDAO.getListArticleBasketForCustomer(customer);
    }

    @Override
    public Basket getBasketForCustomer(Customer customer) {
        return basketDAO.getBasketForCustomer(customer);
    }

    @Override
    public Basket addNewBasket(Basket basket) {
        System.out.println("ICI BASKET = " + basket.getTotalPrice());
        return basketDAO.addNewBasket(basket);
    }

    @Override
    public Basket findBasket(Customer customer) {
        return basketDAO.findBasket(customer);
    }

    @Override
    public void deleteBasketBuyedForCustomer(Customer customer) {
        basketDAO.deleteBasketBuyedForCustomer(customer);
    }
/*
    @Override
    public ArticleSupplierDTO basketToArticle(Basket basket) {
        ArticleInformation articleInformation =articleInformationDAO.getArticleInformationByArticle(basket.getArticles());
        ArticleSupplierDTO articleSupplierDTO = new ArticleSupplierDTO
                basket.getArticles(), articleInformation.getId(),basket.getArticles().getName(),
                basket.getSupplier(), basket.getTotalPrice(), basket.getQuantite()
        );
        return articleSupplierDTO;
    }*/
}
