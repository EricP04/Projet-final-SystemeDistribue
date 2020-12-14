package com.example.demo.Controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Form.Customer.CustomerForm;
import com.example.demo.Form.Customer.CustomerValidator;
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
@RequestMapping(value ="/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerValidator customerValidator;

    @PostMapping("/loginCheck")
    public String loginCheck()
    {
        return "";
    }

    @InitBinder
    protected void InitBinder(WebDataBinder dataBinder)
    {
        Object target = dataBinder.getTarget();
        if(target == null)
            return;

        if(target.getClass() == CustomerForm.class)
            dataBinder.setValidator(customerValidator);
    }


    @RequestMapping(value="/customerRegister", method = RequestMethod.GET)
    public String viewCustomerRegister(Model model)
    {
        CustomerForm customerForm = new CustomerForm();
        model.addAttribute("customerForm",customerForm);

        return "customerRegisterPage";
    }

    @RequestMapping(value="/customerRegister", method = RequestMethod.POST)
    public String saveNewCustomer(Model model, @ModelAttribute("customerForm") @Validated CustomerForm customerForm, //
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
            customer = customerService.createCustomerFromForm(customerForm);
        }
        catch(Exception e)
        {
            /**
             * @Todo : Add log file and hide exception's detail client side
             */
            model.addAttribute("errorMessage","Exception : " +e.getMessage());
            return "customerRegisterPage";
        }

        return "shop";
    }


}
