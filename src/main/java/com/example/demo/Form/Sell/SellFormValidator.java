package com.example.demo.Form.Sell;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.ArticleInformation;
import com.example.demo.Entity.Supplier;
import com.example.demo.Form.Login.LoginForm;
import com.example.demo.Form.Login.LoginSupplierForm;
import com.example.demo.Repository.Article.ArticleDAO;
import com.example.demo.Repository.ArticleInformation.ArticleInformationDAO;
import com.example.demo.Repository.Supplier.SupplierDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SellFormValidator implements Validator {


    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private ArticleInformationDAO articleInformationDAO;

    @Autowired
    private SupplierDAO supplierDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == SellForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        SellForm sellForm = (SellForm) o;
        ValidationUtils.rejectIfEmpty(errors, "name", "NotEmpty.sellForm.name");
        ValidationUtils.rejectIfEmpty(errors, "stock", "NotEmpty.sellForm.stock");
        ValidationUtils.rejectIfEmpty(errors, "price", "NotEmpty.sellForm.price");

        ///Est-ce que l'article existe déja en db? Si oui on le récupère et on lui ajoute les informations du form
        //Si non, on le crée, on lui ajoute les info

        Article article = articleDAO.getArticleByName(sellForm.getName());
        if(article==null)
        {
            ///On le crée
            article = new Article(sellForm.getName());
            if(!articleDAO.saveNewArticle(article))
            {
                ///Error
                return;
            }
        }

        Supplier supplier = supplierDAO.searchById(sellForm.getIdSupplier());
        if(supplier==null)
        {
            ///Error
            return;
        }

        ArticleInformation articleInformation = articleInformationDAO.getInformationForOneArticleFromOneSupplier(article,supplier);
        if(articleInformation==null)
        {
            ///On le crée
            articleInformation = new ArticleInformation(supplier, article, sellForm.getPrice(), sellForm.getStock());
            if(!articleInformationDAO.saveNewArticleInformation(articleInformation))
            {
                return ;

            }
        }
        else
        {
            articleInformation.setPrice(sellForm.getPrice());
            articleInformation.setStock(sellForm.getStock());
        }

        if(!articleInformationDAO.saveNewArticleInformation(articleInformation))
        {

            ///Error
            errors.rejectValue("emailAddress","Pattern.customerRegisterForm.email");
            return;
        }




    }
}
