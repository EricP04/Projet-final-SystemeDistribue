package com.example.demo.Controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Login.LoginCustomerForm;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Customer.CustomerLoginValidator;
import com.example.demo.Form.Login.LoginSupplierForm;
import com.example.demo.Service.Customer.CustomerService;
import com.example.demo.Service.Supplier.SupplierService;
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
public class LoginController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CustomerLoginValidator customerLoginValidator;

    @InitBinder
    protected void InitBinder(WebDataBinder dataBinder)
    {
        Object target = dataBinder.getTarget();
        if(target == null)
            return;

        if(target.getClass() == LoginForm.class)
            dataBinder.setValidator(customerLoginValidator);
    }


    @GetMapping("/connexion")
    public String index(Model model)
    {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginFormCustomer", new LoginCustomerForm());
        model.addAttribute("loginFormSupplier",new LoginSupplierForm());
        return "loginPage";
    }





    @RequestMapping(value="/loginCustomerCheck", method = RequestMethod.POST)
    public String checkCustomer(Model model, @ModelAttribute("loginFormCustomer") @Validated LoginCustomerForm loginForm, //
                                   BindingResult result,
                                   final RedirectAttributes redirectAttributes)
    {
        System.out.println("CHECK NEW CUSTOMER : email = " + loginForm.getEmailAddress() + " password = " + loginForm.getPassword());
        if(result.hasErrors())
        {
            /**
             * @TODO : Debug trace
             */
            for(ObjectError t : result.getAllErrors())
            {
                System.out.println("Erreur : " + t.toString());
            }
            System.out.println("Il y a des erreurs ici");
            model.addAttribute("loginFormSupplier",new LoginSupplierForm());
            return "loginPage";
        }
        Customer customer = null;
        try{
            customer = customerService.searchCustomerFromForm(loginForm);
        }
        catch(Exception e)
        {
            /**
             * @Todo : Add log file and hide exception's detail client side
             */
            model.addAttribute("errorMessageCustomer","Exception : " +e.getMessage());
            model.addAttribute("loginFormSupplier",new LoginSupplierForm());
            return "loginPage";
        }


        return "shop";
    }

    @RequestMapping(value="/loginSupplierCheck", method = RequestMethod.POST)
    public String checkSupplier(Model model, @ModelAttribute("loginFormSupplier") @Validated LoginSupplierForm loginForm, //
                                   BindingResult result,
                                   final RedirectAttributes redirectAttributes)
    {
        System.out.println("CHECK SUPPLIER");
        if(result.hasErrors())
        {
            /**
             * @TODO : Debug trace
             */
            for(ObjectError t : result.getAllErrors())
            {
                System.out.println("Erreur : " + t.toString());
            }
            System.out.println("Il y a des erreurs ici");
            model.addAttribute("loginFormCustomer",new LoginCustomerForm());

            return "loginPage";
        }
        Supplier supplier = null;
        try{
            supplier = supplierService.searchSupplierFromForm(loginForm);
        }
        catch(Exception e)
        {
            /**
             * @Todo : Add log file and hide exception's detail client side
             */
            model.addAttribute("errorMessageCustomer","Exception : " +e.getMessage());
            model.addAttribute("loginFormCustomer",new LoginCustomerForm());

            return "loginPage";
        }


        return "redirect:/sell/";
    }







}
