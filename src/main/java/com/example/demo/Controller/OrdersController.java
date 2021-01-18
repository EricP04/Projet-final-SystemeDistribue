package com.example.demo.Controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Service.Customer.CustomerService;
import com.example.demo.Service.Order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String index(Model model,@CookieValue(value = "CUSTOMER", defaultValue = "-1")String customerEmail)
    {
        Customer customer = customerService.searchCustomerByMail(customerEmail);
        model.addAttribute("listOrders",orderService.getOrdersForCustomer(customer));
        return "orders";
    }

}
