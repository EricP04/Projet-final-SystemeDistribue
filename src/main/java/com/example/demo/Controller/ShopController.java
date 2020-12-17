package com.example.demo.Controller;

import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.Entity.*;
import com.example.demo.Service.Article.ArticleService;
import com.example.demo.Service.ArticleInfomation.ArticleInformationService;
import com.example.demo.Service.ArticleOrderInformation.ArticleOrderInformationService;
import com.example.demo.Service.Basket.BasketService;
import com.example.demo.Service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleInformationService articleInformationService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private ArticleOrderInformationService articleOrderInformationService;

    private List<ArticleSupplierDTO> listArticleSupplierDTOS;
    private List<ArticleSupplierDTO> listBaskets;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,
                        @CookieValue(value = "CUSTOMER", defaultValue = "-1")String customerEmail,
                        HttpSession session)
    {

        model.addAttribute("listArticle",getListArticleSupplierDTOS());
        model.addAttribute("listBaskets",getListBaskets(customerEmail,session));
        return "shop";
    }
    private List<ArticleSupplierDTO> getListArticleSupplierDTOS ()
    {
        return articleService.getListArticleWithCheaperSupplier();
    }
    private List<ArticleSupplierDTO> getListBaskets(String customerEmail, HttpSession session)
    {
        if(customerEmail.compareTo("-1")!=0)
        {
            Customer customer = customerService.searchCustomerByMail(customerEmail);
            if(customer==null)
            {
                System.out.println("CUSTOMER EST NULL");
                listBaskets = new ArrayList<>();
            }
            else
            {
                listBaskets = basketService.getBasketForCustomer(customer);
            }

        }
        else
        {
            listBaskets =(List<ArticleSupplierDTO>) session.getAttribute("basket");
            if(listBaskets ==null)
                listBaskets = new ArrayList<>();
        }
        return listBaskets;
    }
/*
    private HashMap<String, List<ArticleSupplierDTO>> setModelAttribute(String customerEmail, HttpSession session)
    {
        listArticleSupplierDTOS = articleService.getListArticleWithCheaperSupplier();
        if(customerEmail.compareTo("-1")!=0)
        {
            Customer customer = customerService.searchCustomerByMail(customerEmail);
            if(customer==null)
            {
                System.out.println("CUSTOMER EST NULL");
                listBaskets = new ArrayList<>();
            }
            else
            {
                listBaskets = basketService.getBasketForCustomer(customer);
            }

        }
        else
        {
            listBaskets =(List<ArticleSupplierDTO>) session.getAttribute("basket");
            if(listBaskets ==null)
                listBaskets = new ArrayList<>();
        }
        HashMap<String, List<ArticleSupplierDTO>> returnedHashMap = new HashMap<>();
        returnedHashMap.put("listArticle",listArticleSupplierDTOS);
        returnedHashMap.put("listBaskets",listArticleSupplierDTOS);
        return  returnedHashMap;

    }*/

    @RequestMapping(value ="/moreSupplier", method = RequestMethod.POST)
    public String moreSupplier( Model model,
                               @RequestParam(value ="moreSupplierButton", required = true) String buttonValue,
                                @CookieValue(value = "CUSTOMER", defaultValue = "-1")String customerEmail,
                                HttpSession session)
    {
        System.out.println("MORE SUPPLIER BUTTON VALUE = " + buttonValue);
        //model.addAttribute("listArticle",listArticleSupplierDTOS);
        model.addAttribute("listArticle",getListArticleSupplierDTOS());
        model.addAttribute("listBaskets",getListBaskets(customerEmail,session));
        return "shop";
    }

    @RequestMapping(value = "/addToBasket", method = RequestMethod.POST)
    public String addToBasket(Model model,
                              @RequestParam(value="buyButton",required = true) String buttonValue,
                              @RequestParam(value = "stockRequired",required = true) String stockRequired,
                              @CookieValue(value = "CUSTOMER", defaultValue = "-1")String customerEmail,
                              HttpServletResponse response,
                              HttpSession session)
    {
        System.out.println("Customer =" + customerEmail);
        System.out.println("BuyButton = " + buttonValue);
        System.out.println("StockRequired = "+stockRequired);
        Integer idArticleInformation = Integer.valueOf(buttonValue);
        int quantite = 0;
        try{
            quantite = Integer.valueOf(stockRequired);
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException : " + nfe);
            return "shop";
        }
        Customer customer = customerService.searchCustomerByMail(customerEmail);
        if(customer == null)
        {
            System.out.println("CUSTOMER EST NULL QUAND ON LE RECHERCHE");
            return "shop";
        }

        ArticleInformation articleInformation = articleInformationService.getArticleInformationById(idArticleInformation);

        if(articleInformation.getStock()>=quantite) {
            Basket newBasket = basketService.findBasket(customer);
            if (newBasket == null) {
                System.out.println("BASKET = NULL");
                newBasket = new Basket(customer, articleInformation.getPrice() * quantite);
            }
            try {
                newBasket = basketService.addNewBasket(newBasket);
                if (newBasket == null) {
                    return "shop";
                }
            } catch (Exception e) {
                return "shop";
            }
            ArticleOrderInformation articleOrderInformation = articleOrderInformationService.isInList(newBasket.getArticles(),articleInformation.getArticle(),articleInformation.getSupplier());
            if(articleOrderInformation==null) {
                articleOrderInformation = new ArticleOrderInformation(articleInformation.getSupplier(), articleInformation.getArticle(), articleInformation.getPrice(), quantite);
                articleOrderInformation = articleOrderInformationService.newArticleOrderInformation(articleOrderInformation);
                if(articleOrderInformation == null)
                {
                    System.out.println("ERREURS LORS DE LA SAUVEGARDE DU NOUVEL ARTICLE ORDER INFORMATION");
                    return "shop";
                }
            }
            else
            {
                articleOrderInformation.setCount(articleOrderInformation.getCount() + quantite);
            }
            newBasket.addArticle(articleOrderInformation);

            System.out.println("Newtbasket id = " + newBasket.getId());
            newBasket = basketService.addNewBasket(newBasket);
            if (newBasket == null) {
                System.out.println("Erreur lors du save de newbasket");

            } else {
                articleInformation.setStock(articleInformation.getStock() - quantite);
                articleInformation = articleInformationService.saveNewArticleInformation(articleInformation);
                if (articleInformation == null) {
                    System.out.println("Erreur lors de la mise à jour de articleInformation");
                } else {
                    //listBaskets.add(basketService.basketToArticle(newBasket));
                }
            }
        }
        else
        {
            System.out.println("Pas assez de stock");
        }
        model.addAttribute("listArticle",getListArticleSupplierDTOS());
        model.addAttribute("listBaskets",getListBaskets(customerEmail,session));
        return "shop";
    }
}
