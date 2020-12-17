package com.example.demo.Repository.Basket;

import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Basket;
import com.example.demo.Entity.Customer;

import java.util.List;

public interface IBasketDAO {

    List<ArticleSupplierDTO> getBasketForCustomer(Customer customer);
    Basket addNewBasket(Basket basket);
    Basket findBasket(Customer customer);

}
