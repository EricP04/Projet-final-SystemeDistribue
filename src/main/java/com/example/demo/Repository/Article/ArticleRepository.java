package com.example.demo.Repository.Article;

import com.example.demo.Entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article , Integer> {

}
