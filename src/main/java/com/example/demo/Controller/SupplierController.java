package com.example.demo.Controller;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Customer.CustomerRegisterForm;
import com.example.demo.Form.Customer.CustomerRegisterValidator;
import com.example.demo.Form.Supplier.SupplierRegisterForm;
import com.example.demo.Form.Supplier.SupplierRegisterValidator;
import com.example.demo.Service.Customer.CustomerService;
import com.example.demo.Service.Supplier.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value ="/supplier")
@Slf4j
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierRegisterValidator supplierRegisterValidator;

    @InitBinder
    protected void InitBinder(WebDataBinder dataBinder)
    {
        Object target = dataBinder.getTarget();
        if(target == null)
            return;

        if(target.getClass() == SupplierRegisterForm.class)
            dataBinder.setValidator(supplierRegisterValidator);
    }

    //region RegisterCustomer

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String viewSupplierRegister(Model model)
    {
        SupplierRegisterForm supplierRegisterForm = new SupplierRegisterForm();
        model.addAttribute("supplierRegisterForm", supplierRegisterForm);

        return "supplierRegisterPage";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String saveNewSupplier(Model model, @ModelAttribute("supplierRegisterForm") @Validated SupplierRegisterForm supplierRegisterForm, //
                                  BindingResult result,
                                  final RedirectAttributes redirectAttributes)
    {
        if(result.hasErrors())
        {
            for(ObjectError t : result.getAllErrors())
            {
                log.error("Erreur : " + t.toString());
            }
            return "supplierRegisterPage";
        }
        Supplier supplier = null;
        try{
            supplier = supplierService.createSupplierFromFormRegister(supplierRegisterForm);
        }
        catch(Exception e)
        {
            /**
             * @Todo : Add log file and hide exception's detail client side
             */
            model.addAttribute("errorMessage","Exception : " +e.getMessage());
            return "supplierRegisterPage";
        }

        return "redirect:/sell/";
    }



}
