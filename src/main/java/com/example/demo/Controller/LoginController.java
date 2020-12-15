package com.example.demo.Controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Customer.CustomerLoginForm;
import com.example.demo.Form.Customer.CustomerLoginValidator;
import com.example.demo.Form.Customer.CustomerRegisterForm;
import com.example.demo.Service.CustomerService;
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
    private CustomerLoginValidator customerLoginValidator;

    @InitBinder
    protected void InitBinder(WebDataBinder dataBinder)
    {
        Object target = dataBinder.getTarget();
        if(target == null)
            return;

        if(target.getClass() == CustomerLoginForm.class)
            dataBinder.setValidator(customerLoginValidator);
    }


    @GetMapping("/connexion")
    public String index(Model model)
    {
        CustomerLoginForm customerLoginForm = new CustomerLoginForm();
        model.addAttribute("customerLoginForm",customerLoginForm);
        return "loginPage";
    }




    ///region Login customer
    @RequestMapping(value="/loginCustomerCheck", method = RequestMethod.POST)
    public String checkNewCustomer(Model model, @ModelAttribute("customerLoginForm") @Validated CustomerLoginForm customerLoginForm, //
                                  BindingResult result,
                                  final RedirectAttributes redirectAttributes)
    {
        System.out.println("CHECK NEW CUSTOMER : email = " + customerLoginForm.getEmailAddress() + " password = " + customerLoginForm.getPassword());
        if(result.hasErrors())
        {
            /**
             * @TODO : Debug trace
             */
            for(ObjectError t : result.getAllErrors())
            {
                System.out.println("Erreur : " + t.toString());
            }
            System.out.println("Il y a des erreurs");
            return "loginPage";
        }
        Customer customer = null;
        try{
            customer = customerService.searchCustomerFromForm(customerLoginForm);
        }
        catch(Exception e)
        {
            /**
             * @Todo : Add log file and hide exception's detail client side
             */
            model.addAttribute("errorMessageCustomer","Exception : " +e.getMessage());
            return "loginPage";
        }


        return "shop";
    }
    ///endregion






}
