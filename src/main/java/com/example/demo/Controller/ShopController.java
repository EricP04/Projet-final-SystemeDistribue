package com.example.demo.Controller;

import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.Entity.*;
import com.example.demo.Service.Article.ArticleService;
import com.example.demo.Service.ArticleInfomation.ArticleInformationService;
import com.example.demo.Service.ArticleOrderInformation.ArticleOrderInformationService;
import com.example.demo.Service.Basket.BasketService;
import com.example.demo.Service.Customer.CustomerService;
import com.example.demo.Service.TVA.TvaTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    private TvaTemplateService tvaTemplateService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,
                        @CookieValue(value = "CUSTOMER", defaultValue = "-1")String customerEmail,
                        HttpSession session)
    {
        System.out.println("CUSTOMER EMAIL =" + customerEmail);
        model.addAttribute("customer",customerService.searchCustomerByMail(customerEmail));
        model.addAttribute("listArticle",getListArticleSupplierDTOS());
        model.addAttribute("listBaskets",getListBaskets(customerEmail,session));
        model.addAttribute("totalCost",getTotalPrice(customerEmail,session));

        return "shop";
    }
    private List<ArticleSupplierDTO> getListArticleSupplierDTOS ()
    {
        return articleService.getListArticleWithCheaperSupplier();
    }
    private List<ArticleSupplierDTO> getListBaskets(String customerEmail, HttpSession session)
    {
        List<ArticleSupplierDTO> listBaskets;

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
                listBaskets = basketService.getListArticleBasketForCustomer(customer);
            }

        }
        else
        {
            Basket basket = (Basket)session.getAttribute("basket");
            if(basket==null)
            {
                session.setAttribute("basket",new Basket(0));

            }
            listBaskets = articleOrderInformationService.convertToListArticleSupplierDTO(basket.getArticles());

            /*listBaskets =(List<ArticleSupplierDTO>) session.getAttribute("basket");
            if(listBaskets ==null) {
                listBaskets = new ArrayList<>();
                session.setAttribute("basket",listBaskets);
            }*/
        }
        return listBaskets;
    }
    private double getTotalPrice(String customerEmail, HttpSession session)
    {

        if(customerEmail.compareTo("-1")!=0)
        {
            Customer customer = customerService.searchCustomerByMail(customerEmail);
            if(customer==null)
            {
                System.out.println("CUSTOMER EST NULL");
                return 0;
            }
            else
            {
                Basket basket = basketService.getBasketForCustomer(customer);
                if(basket == null) {
                    basket=new Basket(0);
                    return basket.getTotalPrice();
                }
                return basket.getTotalPrice();
            }

        }
        else
        {
            Basket basket = (Basket) session.getAttribute("basket");
            if(basket!=null)
                return basket.getTotalPrice();
        }
        return 0;
    }


    @RequestMapping(value ="/moreSupplier", method = RequestMethod.POST)
    public String moreSupplier( Model model,
                               @RequestParam(value ="moreSupplierButton", required = true) String buttonValue,
                                @CookieValue(value = "CUSTOMER", defaultValue = "-1")String customerEmail,
                                HttpSession session)
    {
        System.out.println("MORE SUPPLIER BUTTON VALUE = " + buttonValue);
        //model.addAttribute("listArticle",listArticleSupplierDTOS);
        model.addAttribute("customer",customerService.searchCustomerByMail(customerEmail));
        model.addAttribute("listArticle",getListArticleSupplierDTOS());
        model.addAttribute("listBaskets",getListBaskets(customerEmail,session));
        model.addAttribute("totalCost",getTotalPrice(customerEmail,session));
        return "shop";
    }
    @RequestMapping(value = "/removeFromBasket", method = RequestMethod.POST)
    public String removeFromBasket(Model model,
                                   @RequestParam(value="removeButton",required = true) String buttonValue,
                                   @CookieValue(value = "CUSTOMER", defaultValue = "-1")String customerEmail,
                                   HttpServletResponse response,
                                   HttpSession session){
        Integer idArticleInformation = Integer.valueOf(buttonValue);
        List<ArticleSupplierDTO> articlesBasket = getListBaskets(customerEmail,session);
        ArticleSupplierDTO articleInformation = null;
        Customer customer = customerService.searchCustomerByMail(customerEmail);
        for(ArticleSupplierDTO articleSupplierDTO : articlesBasket)
        {
            if(articleSupplierDTO.getIdArticleInfo() == idArticleInformation) {
                articleInformation = articleSupplierDTO;
                break;
            }
        }
        if(articleInformation!=null) {
            System.out.println("Id article information : " + articleInformation.getIdArticleInfo());
            System.out.println("Quantite de l'article : " + articleInformation.getStock());
            ArticleInformation articleToSupp = articleInformationService.getArticleInformationById(idArticleInformation);
            System.out.println("Article to supp : " + articleToSupp.getId());
            Basket basket = basketService.getBasketForCustomer(customer);
            System.out.println("Basket article 1 name : " + basket.getArticles().get(0).getArticle().getName());
            System.out.println("article to supp 1 name : " + articleToSupp.getArticle().getName());
            System.out.println("Basket article 1 name : " + basket.getArticles().get(0).getArticle().getId());
            System.out.println("article to supp 1 name : " + articleToSupp.getArticle().getId());
            boolean find = false;
            for(int i=0;i<basket.getArticles().size() && !find;i++)
            {

                if(basket.getArticles().get(i).getArticle().getId()==idArticleInformation)
                {
                    find=basket.getArticles().remove(i)!=null;
                }
            }
            if(find) {
                System.out.print("BASKET CONTIENT CET ARTICLE");
                articleToSupp.setStock(articleToSupp.getStock() + articleInformation.getStock());
                double totalPrice = tvaTemplateService.getTotalPriceWithTva(basket.getArticles());
                basket.setTotalPrice(totalPrice);
                basketService.addNewBasket(basket);
                articleInformationService.saveNewArticleInformation(articleToSupp);
            }
            else {
                System.out.print("BASKET NE CONTIENT PAS CET ARTICLE");
            }
        }

        return "redirect:/shop/";
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
                newBasket = new Basket(customer);
            }
            try {
                //newBasket.setTotalPrice();
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

            double totalPrice = tvaTemplateService.getTotalPriceWithTva(newBasket.getArticles());
            newBasket.setTotalPrice(totalPrice);
            System.out.println("Newtbasket id = " + newBasket.getId());
            System.out.println("NEWBASKET TOTAL PRICE :" + totalPrice);
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
        model.addAttribute("customer",customerService.searchCustomerByMail(customerEmail));
        model.addAttribute("listArticle",getListArticleSupplierDTOS());
        model.addAttribute("listBaskets",getListBaskets(customerEmail,session));
        model.addAttribute("totalCost",getTotalPrice(customerEmail,session));

        return "shop";
    }
}
