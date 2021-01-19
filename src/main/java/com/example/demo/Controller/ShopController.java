package com.example.demo.Controller;

import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.Entity.*;
import com.example.demo.Service.Article.ArticleService;
import com.example.demo.Service.ArticleInfomation.ArticleInformationService;
import com.example.demo.Service.ArticleOrderInformation.ArticleOrderInformationService;
import com.example.demo.Service.Basket.BasketService;
import com.example.demo.Service.Customer.CustomerService;
import com.example.demo.Service.TVA.TvaTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop")
@Slf4j
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
                        HttpSession session)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();
        if(!customerEmail.equals("anonymousUser")) {
            Basket basket = (Basket) session.getAttribute("basket");
            if (basket != null) {
                if(setCustomerBasket(basket,customerEmail))
                {
                    session.setAttribute("basket",null);
                }
            }
        }
        log.debug("Customer email : " + customerEmail);
        model.addAttribute("customer",customerService.searchCustomerByMail(customerEmail));
        model.addAttribute("listArticle",getListArticleSupplierDTOS());
        model.addAttribute("listBaskets",getListBaskets(customerEmail,session));
        model.addAttribute("totalCost",getTotalPrice(customerEmail,session));
        return "shop";
    }

    private boolean setCustomerBasket(Basket b, String customerEmail) {
        log.debug("set customer basket (email = " + customerEmail);
        Customer customer = customerService.searchCustomerByMail(customerEmail);
        if (customer == null) {
            log.error("Customer non trouvé");
            return false;
        }
        Basket basketCustomer = basketService.getBasketForCustomer(customer);
        if (basketCustomer == null) {
            basketCustomer = new Basket(0);
            basketCustomer.setCustomer(customer);
        }
        List<ArticleOrderInformation> articleInformations = b.getArticles();
        for (ArticleOrderInformation articleOrderInformation : articleInformations)
        {
            basketCustomer.addArticle(articleOrderInformation);
        }
        basketCustomer.setTotalPrice(tvaTemplateService.getTotalPriceWithTva(basketCustomer.getArticles()));
        return basketService.addNewBasket(basketCustomer)!=null;


    }
    private List<ArticleSupplierDTO> getListArticleSupplierDTOS ()
    {
        return articleService.getListArticleWithCheaperSupplier();
    }
    private List<ArticleSupplierDTO> getListBaskets(String customerEmail, HttpSession session)
    {
        List<ArticleSupplierDTO> listBaskets;

        if(customerEmail.equals("anonymousUser")==false)
        {
            Customer customer = customerService.searchCustomerByMail(customerEmail);
            if(customer==null)
            {
                log.debug("Customer null");
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
                basket = new Basket(0);
                session.setAttribute("basket",basket);

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

        if(customerEmail.compareTo("anonymousUser")!=0)
        {
            Customer customer = customerService.searchCustomerByMail(customerEmail);
            if(customer==null)
            {
                log.error("Customer est null");
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

                                HttpSession session)
    {
        log.debug("More supplier");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();
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
                                   HttpServletResponse response,
                                   HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();
        Integer idArticleInformation = Integer.valueOf(buttonValue);
        ArticleSupplierDTO articleInformation = null;
        List<ArticleSupplierDTO> articlesBasket = getListBaskets(customerEmail,session);

        if(!customerEmail.equals("anonymousUser")) {

            Customer customer = customerService.searchCustomerByMail(customerEmail);
            for (ArticleSupplierDTO articleSupplierDTO : articlesBasket) {
                if (articleSupplierDTO.getIdArticleInfo() == idArticleInformation) {
                    articleInformation = articleSupplierDTO;
                    break;
                }
            }
            if (articleInformation != null) {
                ArticleInformation articleToSupp = articleInformationService.getArticleInformationById(idArticleInformation);
                Basket basket = basketService.getBasketForCustomer(customer);
                boolean find = false;
                for (int i = 0; i < basket.getArticles().size() && !find; i++) {

                    if (basket.getArticles().get(i).getArticle().getId() == idArticleInformation) {
                        find = basket.getArticles().remove(i) != null;
                    }
                }
                if (find) {
                    articleToSupp.setStock(articleToSupp.getStock() + articleInformation.getStock());
                    double totalPrice = tvaTemplateService.getTotalPriceWithTva(basket.getArticles());
                    basket.setTotalPrice(totalPrice);
                    basketService.addNewBasket(basket);
                    articleInformationService.saveNewArticleInformation(articleToSupp);
                } else {
                    log.error("le panier ne contient pas cet article");
                }
            }
        }
        else
        {
            Basket basket = (Basket) session.getAttribute("basket");
            for (ArticleSupplierDTO articleSupplierDTO : articlesBasket) {
                if (articleSupplierDTO.getIdArticleInfo() == idArticleInformation) {
                    articleInformation = articleSupplierDTO;
                    break;
                }
            }
            if (articleInformation != null) {
                ArticleInformation articleToSupp = articleInformationService.getArticleInformationById(idArticleInformation);
                boolean find = false;
                for (int i = 0; i < basket.getArticles().size() && !find; i++) {

                    if (basket.getArticles().get(i).getArticle().getId() == idArticleInformation) {
                        find = basket.getArticles().remove(i) != null;
                    }
                }
                if (find) {
                    articleToSupp.setStock(articleToSupp.getStock() + articleInformation.getStock());
                    double totalPrice = tvaTemplateService.getTotalPriceWithTva(basket.getArticles());
                    basket.setTotalPrice(totalPrice);
                    articleInformationService.saveNewArticleInformation(articleToSupp);
                    session.setAttribute("basket",basket);
                } else {
                    log.debug("Le panier ne contient pas cet article");
                }
            }
        }

        return "redirect:/shop/";
    }

    @RequestMapping(value = "/addToBasket", method = RequestMethod.POST)
    public String addToBasket(Model model,
                              @RequestParam(value="buyButton",required = true) String buttonValue,
                              @RequestParam(value = "stockRequired",required = true) String stockRequired,
                              HttpServletResponse response,
                              HttpSession session)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();
        Integer idArticleInformation = Integer.valueOf(buttonValue);
        int quantite = 0;
        try{
            quantite = Integer.valueOf(stockRequired);
        }
        catch (NumberFormatException nfe)
        {
            log.error("NumberFormatException : " + nfe);
            return "redirect:/shop/";
        }
        ArticleInformation articleInformation = articleInformationService.getArticleInformationById(idArticleInformation);

        if(!customerEmail.equals("anonymousUser")) {
            Customer customer = customerService.searchCustomerByMail(customerEmail);
            if (customer == null) {
                log.error("Customer null quand on le recherche");
                return "redirect:/shop/";
            }


            if (articleInformation.getStock() >= quantite) {
                Basket newBasket = basketService.findBasket(customer);
                if (newBasket == null) {
                    newBasket = new Basket(customer);
                }
                try {
                    //newBasket.setTotalPrice();
                    newBasket = basketService.addNewBasket(newBasket);
                    if (newBasket == null) {
                        return "redirect:/shop/";
                    }
                } catch (Exception e) {
                    return "redirect:/shop/";
                }
                ArticleOrderInformation articleOrderInformation = articleOrderInformationService.isInList(newBasket.getArticles(), articleInformation.getArticle(), articleInformation.getSupplier());
                if (articleOrderInformation == null) {
                    articleOrderInformation = new ArticleOrderInformation(articleInformation.getSupplier(), articleInformation.getArticle(), articleInformation.getPrice(), quantite);
                    articleOrderInformation = articleOrderInformationService.newArticleOrderInformation(articleOrderInformation);
                    if (articleOrderInformation == null) {
                        log.error("Erreurs lors de la sauvegarde du nouvel article order information");
                        return "redirect:/shop/";
                    }
                } else {
                    articleOrderInformation.setCount(articleOrderInformation.getCount() + quantite);
                }
                newBasket.addArticle(articleOrderInformation);

                double totalPrice = tvaTemplateService.getTotalPriceWithTva(newBasket.getArticles());
                newBasket.setTotalPrice(totalPrice);
                newBasket = basketService.addNewBasket(newBasket);
                if (newBasket == null) {
                    log.error("Erreurs lors du save de newbasket");

                } else {
                    articleInformation.setStock(articleInformation.getStock() - quantite);
                    articleInformation = articleInformationService.saveNewArticleInformation(articleInformation);
                    if (articleInformation == null) {
                        log.error("Erreur lors de la mise à jour de articleInformation");
                    }
                }

            } else {
                log.debug("Pas assez de stock");
            }
        }
        else
        {
            Basket b = (Basket) session.getAttribute("basket");
            if(b == null)
                b = new Basket(0);
            ArticleOrderInformation articleOrderInformation = articleOrderInformationService.isInList(b.getArticles(), articleInformation.getArticle(), articleInformation.getSupplier());
            if (articleOrderInformation == null) {
                articleOrderInformation = new ArticleOrderInformation(articleInformation.getSupplier(), articleInformation.getArticle(), articleInformation.getPrice(), quantite);
                articleOrderInformation = articleOrderInformationService.newArticleOrderInformation(articleOrderInformation);
                if (articleOrderInformation == null) {
                    log.error("Erreurs lors de la sauvegarde du nouvel article");
                    return "shop";
                }
            } else {
                articleOrderInformation.setCount(articleOrderInformation.getCount() + quantite);
            }
            b.addArticle(articleOrderInformation);
            b.setTotalPrice(tvaTemplateService.getTotalPriceWithTva(b.getArticles()));
            session.setAttribute("basket",b);
            articleInformation.setStock(articleInformation.getStock() - quantite);
            articleInformation = articleInformationService.saveNewArticleInformation(articleInformation);
            if (articleInformation == null) {
                log.error("Erreur lors de la mise à jour de articleInformation");
            }
        }
        model.addAttribute("customer",customerService.searchCustomerByMail(customerEmail));
        model.addAttribute("listArticle",getListArticleSupplierDTOS());
        model.addAttribute("listBaskets",getListBaskets(customerEmail,session));
        model.addAttribute("totalCost",getTotalPrice(customerEmail,session));

        return "redirect:/shop/";
    }
}
