package com.example.demo.Controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Service.Customer.CustomerService;
import com.example.demo.Service.Order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(name="/",method = RequestMethod.GET)
    public String index(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();
        Customer customer = customerService.searchCustomerByMail(customerEmail);
        model.addAttribute("listOrders",orderService.getOrdersForCustomer(customer));
        return "orders";
    }

}
