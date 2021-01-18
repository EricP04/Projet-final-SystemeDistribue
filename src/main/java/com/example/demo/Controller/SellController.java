package com.example.demo.Controller;

import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Login.LoginCustomerForm;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Login.LoginSupplierForm;
import com.example.demo.Form.Sell.SellForm;
import com.example.demo.Form.Sell.SellFormValidator;
import com.example.demo.Service.Article.ArticleService;
import com.example.demo.Service.ArticleInfomation.ArticleInformationService;
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
@RequestMapping("/supplier/sell")
public class SellController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SellFormValidator sellFormValidator;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleInformationService articleInformationService;

    @InitBinder
    protected void InitBinder(WebDataBinder dataBinder)
    {
        Object target = dataBinder.getTarget();
        if(target == null)
            return;

        if(target.getClass() == SellForm.class)
            dataBinder.setValidator(sellFormValidator);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    private String index(Model model, @CookieValue(value ="SUPPLIER", defaultValue = "-1") String suppEmail)
    {
        System.out.println("Sell controller");
        //model.addAttribute("articles",articleInformationService.getListArticleDTOFromOneSupplier())

        /**
         * @TODO: Rien a faire ici , il faudra le déplacer dans le POST pour le form
         */
        System.out.println("SUPPLIER EMAIL = " + suppEmail);
        //model.addAttribute("errorMessage","Une erreur s'est produite lors de l'ajout de votre article ...");
        model.addAttribute("sellForm",new SellForm(suppEmail));
        model.addAttribute("listArticle",articleInformationService.getListArticleDTOFromOneSupplier(supplierService.getUserByEmail(suppEmail)));
        return "sell";
    }

    @RequestMapping(value="/",method = RequestMethod.POST)
    private String updateArticle(Model model, @ModelAttribute("sellForm") @Validated SellForm sellForm, //
                                 BindingResult result,
                                 final RedirectAttributes redirectAttributes,
                                 @CookieValue(value = "SUPPLIER", defaultValue = "-1") String suppEmail)
    {
        System.out.println("ICI LA");
        for(ObjectError error : result.getAllErrors())
        {
            System.out.println("Error : " + error);
        }
        model.addAttribute("listArticle",articleInformationService.getListArticleDTOFromOneSupplier(supplierService.getUserByEmail(suppEmail)));
        return "sell";
    }





}
