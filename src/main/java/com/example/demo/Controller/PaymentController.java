package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Message.OrderMessage;
import com.example.demo.Service.Article.ArticleService;
import com.example.demo.Service.ArticleInfomation.ArticleInformationService;
import com.example.demo.Service.ArticleOrderInformation.ArticleOrderInformationService;
import com.example.demo.Service.Basket.BasketService;
import com.example.demo.Service.Customer.CustomerService;
import com.example.demo.Service.Order.OrderService;
import com.example.demo.Service.WarehouseService.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

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
    private OrderService orderService;

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();
        model.addAttribute("price",getTotalPrice(customerEmail,session));
        return "payment";
    }

    @RequestMapping(name="/", method = RequestMethod.POST)
    public String confirmPayment(Model model, HttpSession session,
                                 @RequestParam(value = "radioButton", required = true) String radioButton)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();
        double price = getTotalPrice(customerEmail,session);
        if(radioButton.equals("Standard"))
        {
            price +=5;
        }
        else
        {
            price +=10;
        }
        Customer customer = customerService.searchCustomerByMail(customerEmail);
        if(customer.getFundsAvailable()<price)
        {
            model.addAttribute("errors","Funds available not sufficient (Funds available :" + customer.getFundsAvailable()+")");
            return "payment";
        }
        Basket basket = basketService.getBasketForCustomer(customer);
        List<ArticleOrderInformation> articleOrderInformationListTmp = basket.getArticles();
        basketService.deleteBasketBuyedForCustomer(customer);
        List<ArticleOrderInformation> articleOrderInformationList = new ArrayList<>();
        for(ArticleOrderInformation articleOrderInformation : articleOrderInformationListTmp)
            articleOrderInformationList.add(articleOrderInformationService.newArticleOrderInformation(new ArticleOrderInformation(
                    articleOrderInformation.getSupplier(), articleOrderInformation.getArticle(),articleOrderInformation.getPrice(), articleOrderInformation.getCount()))
            );
        Order newOrder = orderService.newOrder(new Order());
        newOrder.setCustomer(customer);
        Order orderWithId=orderService.newOrder(newOrder);
        newOrder.setStatus(0);
        orderWithId=orderService.newOrder(newOrder);
        newOrder.setArticles(articleOrderInformationList);
        orderWithId=orderService.newOrder(newOrder);

        newOrder.setTotalPrice(price);
         orderWithId=orderService.newOrder(newOrder);



        warehouseService.sendMessageToWarehouse(new OrderMessage(orderWithId.getId(),orderWithId.getStatus()));
        customerService.decreaseFundsAvailable(customer,(float)price);
        return "redirect:/shop/";
    }

    private double getTotalPrice(String customerEmail, HttpSession session)
    {

        if(customerEmail.compareTo("-1")!=0)
        {
            Customer customer = customerService.searchCustomerByMail(customerEmail);
            if(customer==null)
            {
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
}
