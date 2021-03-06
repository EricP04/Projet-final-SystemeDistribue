package com.example.demo.Repository.Basket;

import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.Entity.*;
import com.example.demo.Repository.ArticleInformation.ArticleInformationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasketDAO implements IBasketDAO {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ArticleInformationDAO articleInformationDAO;

    @Override
    public List<ArticleSupplierDTO> getListArticleBasketForCustomer(Customer customer) {
        Iterable<Basket> baskets = basketRepository.findAll();
        List<ArticleSupplierDTO> articleSupplierDTOS = new ArrayList<>();
        for(Basket basket : baskets)
        {
            if(basket.getCustomer()!=null) {
                if (basket.getCustomer().equals(customer)) {

                    for(ArticleOrderInformation articleOrderInformation : basket.getArticles())
                    {
                        ArticleInformation articleInformation = articleInformationDAO.getArticleInformationByArticle(articleOrderInformation.getArticle());
                        articleSupplierDTOS.add(new ArticleSupplierDTO(articleInformation.getArticle().getId(),articleInformation.getId(),
                                articleInformation.getArticle().getName(),
                            articleInformation.getSupplier(), articleOrderInformation.getPrice(), articleOrderInformation.getCount(),articleInformation.getArticle().getType()));
                    }


                }
            }
        }
        return articleSupplierDTOS;
    }

    @Override
    public Basket getBasketForCustomer(Customer customer) {
        Iterable<Basket> baskets = basketRepository.findAll();
        for(Basket basket : baskets)
        {
            if(basket.getCustomer().equals(customer))
                return basket;
        }
        return null;
    }

    @Override
    public Basket addNewBasket(Basket basket) {
        System.out.println("BASKET QU ON VA SAVE :----------------------" + basket.getTotalPrice());
        return basketRepository.save(basket);
    }

    @Override
    public Basket findBasket(Customer customer) {
        Iterable<Basket> baskets = basketRepository.findAll();
        for(Basket basket : baskets)
        {
            if(basket.getCustomer().equals(customer) )
                return basket;
        }
        return null;
    }

    @Override
    public void deleteBasketBuyedForCustomer(Customer customer) {
        Iterable<Basket> baskets = basketRepository.findAll();
        for(Basket basket : baskets)
        {
            if(basket.getCustomer().equals(customer))
            {
                basketRepository.delete(basket);
            }
        }
    }
}
