package com.example.demo.Controller;

import com.example.demo.Form.InsertMoney.InsertMoneyForm;
import com.example.demo.Form.InsertMoney.InsertMoneyValidator;
import com.example.demo.Service.Customer.CustomerService;
import com.example.demo.Service.GRPCBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/money")
public class InsertMoneyController {

    @Autowired
    CustomerService customerService;

    @Autowired
    private InsertMoneyValidator insertMoneyValidator;

    @Autowired
    private GRPCBankService grpcBankService;

    @InitBinder
    protected void InitBinder(WebDataBinder dataBinder)
    {
        System.out.println("INITBINDER");
        Object target = dataBinder.getTarget();
        if(target == null)
            return;

        if(target.getClass() == InsertMoneyForm.class)
            dataBinder.setValidator(insertMoneyValidator);
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,
                        HttpSession session)
    {
        model.addAttribute("insertMoneyForm", new InsertMoneyForm());
        return "insertMoney";
    }


    @RequestMapping(value="/", method = RequestMethod.POST)
    public String insertMoney(Model model, @ModelAttribute("insertMoneyForm") @Validated InsertMoneyForm insertMoneyForm,

                                BindingResult result,
                                final RedirectAttributes redirectAttributes,
                                HttpServletResponse response)
    {
        System.out.println("CHECK SUPPLIER");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();
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

            return "insertMoney";
        }
        if(grpcBankService.askMoney(insertMoneyForm.getNumAccount(),insertMoneyForm.getAmountAsk()))
        {
            customerService.increaseFundsAvailable(customerService.searchCustomerByMail(customerEmail),insertMoneyForm.getAmountAsk());
            return "redirect:/shop/";
        }
        else {
            model.addAttribute("errors","Une erreur est survenue avec la banque, veuillez reessayé plus tard");
        }
    return "insertMoney";
    }

}
