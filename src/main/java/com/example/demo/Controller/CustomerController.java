package com.example.demo.Controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Customer.CustomerRegisterForm;
import com.example.demo.Form.Customer.CustomerRegisterValidator;
import com.example.demo.Service.Article.ArticleService;
import com.example.demo.Service.ArticleInfomation.ArticleInformationService;
import com.example.demo.Service.Customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value ="/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @Autowired
    private CustomerRegisterValidator customerRegisterValidator;



    @InitBinder
    protected void InitBinder(WebDataBinder dataBinder)
    {
        Object target = dataBinder.getTarget();
        if(target == null)
            return;

        if(target.getClass() == CustomerRegisterForm.class)
            dataBinder.setValidator(customerRegisterValidator);
    }

    //region RegisterCustomer

    @RequestMapping(value="/customerRegister", method = RequestMethod.GET)
    public String viewCustomerRegister(Model model)
    {
        CustomerRegisterForm customerRegisterForm = new CustomerRegisterForm();
        model.addAttribute("customerRegisterForm", customerRegisterForm);

        return "customerRegisterPage";
    }

    @RequestMapping(value="/customerRegister", method = RequestMethod.POST)
    public String saveNewCustomer(Model model, @ModelAttribute("customerRegisterForm") @Validated CustomerRegisterForm customerRegisterForm, //
                                  BindingResult result,
                                  final RedirectAttributes redirectAttributes)
    {
        if(result.hasErrors())
        {

            for(ObjectError t : result.getAllErrors())
            {
                System.out.println("Erreur : " + t.toString());
            }
            System.out.println("Il y a des erreurs");
            return "customerRegisterPage";
        }
        Customer customer = null;
        try{
            customer = customerService.createCustomerFromFormRegister(customerRegisterForm);
        }
        catch(Exception e)
        {
            /**
             * @Todo : Add log file and hide exception's detail client side
             */
            model.addAttribute("errorMessage","Exception : " +e.getMessage());
            return "customerRegisterPage";
        }

        return "redirect:/login";
    }

    //endregion






}
