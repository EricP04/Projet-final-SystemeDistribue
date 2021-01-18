package com.example.demo.Service.TVA;

import com.example.demo.DTO.ArticleSupplierDTO;
import com.example.demo.DTO.ArticleTypePriceDTO;
import com.example.demo.Entity.ArticleOrderInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TvaTemplateService {


    @Autowired
    RestTemplate restTemplate;





    public double getTotalPriceWithTva(List<ArticleOrderInformation> articleSupplierDTOList)
    {

        System.out.println("ArticleSupplierDTOList size :" + articleSupplierDTOList.size());
        List<ArticleTypePriceDTO> listArticleTypePriceDTOS = new ArrayList<>();
        for(ArticleOrderInformation articleOrderInformation : articleSupplierDTOList)
        {
            listArticleTypePriceDTOS.add(new ArticleTypePriceDTO(articleOrderInformation.getPrice(),articleOrderInformation.getArticle().getType(),articleOrderInformation.getCount()));
        }
        double result = restTemplate.postForObject(
                "http://localhost:8765/tva-service/tvaService",
                listArticleTypePriceDTOS, double.class
        );
        return result;

    }
}
