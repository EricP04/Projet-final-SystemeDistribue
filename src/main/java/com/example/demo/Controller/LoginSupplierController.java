package com.example.demo.Controller;

import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Customer.CustomerLoginValidator;
import com.example.demo.Form.Login.LoginCustomerForm;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Login.LoginSupplierForm;
import com.example.demo.Form.Supplier.SupplierLoginValidator;
import com.example.demo.Service.Customer.CustomerService;
import com.example.demo.Service.Supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/loginPage/supplier")
public class LoginSupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierLoginValidator supplierLoginValidator;


    @InitBinder
    protected void InitBinder(WebDataBinder dataBinder)
    {
        Object target = dataBinder.getTarget();
        if(target == null)
            return;

        if(target.getClass() == LoginSupplierForm.class)
            dataBinder.setValidator(supplierLoginValidator);
    }


    @GetMapping("/")
    public String index(Model model)
    {
        LoginSupplierForm loginForm = new LoginSupplierForm();
        //model.addAttribute("loginFormCustomer", new LoginCustomerForm());
        model.addAttribute("loginFormSupplier",loginForm);
        return "loginPageSupplier";
    }


    @RequestMapping(value="/", method = RequestMethod.POST)
    public String checkSupplier(Model model, @ModelAttribute("loginFormSupplier") @Validated LoginSupplierForm loginForm, //
                                BindingResult result,
                                final RedirectAttributes redirectAttributes,
                                HttpServletResponse response)
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
            //model.addAttribute("loginFormCustomer",new LoginCustomerForm());

            return "loginPageSupplier";
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
            //model.addAttribute("loginFormCustomer",new LoginCustomerForm());

            return "loginPageSupplier";
        }

        System.out.println("ON VA SET LE COOKIE SUPPLIER AVEC : " + supplier.getEmailAddress());
        Cookie cookie = new Cookie("SUPPLIER",supplier.getEmailAddress());
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/sell/";
    }



}
