package com.example.demo.Controller;

import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Sell.SellFormValidator;
import com.example.demo.Service.Article.ArticleService;
import com.example.demo.Service.ArticleInfomation.ArticleInformationService;
import com.example.demo.Service.Supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sell")
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

        if(target.getClass() == LoginForm.class)
            dataBinder.setValidator(sellFormValidator);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    private String index(Model model)
    {
        System.out.println("Sell controller");
        model.addAttribute("articles",articleInformationService.getInformationForOneArticleFromOneSupplier())

        /**
         * @TODO: Rien a faire ici , il faudra le déplacer dans le POST pour le form
         */

        model.addAttribute("errorMessage","Une erreur s'est produite lors de l'ajout de votre article ...");

        return "sell";
    }

}
