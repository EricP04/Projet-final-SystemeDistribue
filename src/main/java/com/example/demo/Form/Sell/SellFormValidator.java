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
        System.out.println("-------------------VALIDATE SELL FORM-----------");
        ValidationUtils.rejectIfEmpty(errors, "name", "NotEmpty.sellForm.name");
        ValidationUtils.rejectIfEmpty(errors, "stock", "NotEmpty.sellForm.stock");
        ValidationUtils.rejectIfEmpty(errors, "price", "NotEmpty.sellForm.price");



        ///Est-ce que l'article existe déja en db? Si oui on le récupère et on lui ajoute les informations du form
        //Si non, on le crée, on lui ajoute les info
        if(!errors.hasErrors()) {
            System.out.println("VALIDATE PAS D ERREURS");
            Article article = articleDAO.getArticleByName(sellForm.getName());
            if (article == null) {
                ///On le crée
                article = new Article(sellForm.getName());
                article = articleDAO.saveNewArticle(article);
                if(article==null)
                {
                    System.out.println("Article new save fail");
                    return;
                }
            }
            System.out.println("SELL FORM EMAIL = " + sellForm.getEmailSupplier());
            Supplier supplier = supplierDAO.getUserByEmail(sellForm.getEmailSupplier());

            if (supplier == null) {
                System.out.println("SUPPLIER EST NULL");
                ///Error
                return;
            }
            System.out.println("SUPPLIER NON NULL");
            ArticleInformation articleInformation = articleInformationDAO.getInformationForOneArticleFromOneSupplier(article, supplier);
            if (articleInformation == null) {
                System.out.println("ARTICLE INFORMATION NULL, ON CREE");
                ///On le crée
                articleInformation = new ArticleInformation(supplier, article, sellForm.getPrice(), sellForm.getStock());
                articleInformation = articleInformationDAO.saveNewArticleInformation(articleInformation);
                if(articleInformation==null)
                {
                    System.out.println("new Article Information Save FAIL");
                    return;
                }
                //article.addArticleInformation(articleInformation);
            } else {
                System.out.println("ARTICLE INFORMATION NON NULL");
                articleInformation.setPrice(sellForm.getPrice());
                articleInformation.setStock(sellForm.getStock());

            }
article.updateArticleInformation(articleInformation);
            System.out.println("ON SAVE ARTICLE ");
            if(articleDAO.saveNewArticle(article)==null)
            {
                System.out.println("FAIL ARTICLE SAVE");
                errors.rejectValue("name", "Pattern.sellFor.email");
                return;
            }
            System.out.println("ON SAVE ARTICLE INFORMATION");
            if (articleInformationDAO.saveNewArticleInformation(articleInformation)==null) {

                ///Error
                System.out.println("FAIL ARTICLE INFORMATION SAVE");
                errors.rejectValue("name", "Pattern.sellFor.email");
                return;
            }




        }else
            System.out.println("HAS ERRORS!!!");

        System.out.println("FIN DU VALIDATOR");

    }
}
