package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Article;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IArticle {
    public boolean addNewArticle(Article article);
    public boolean updateArticle(Article article);
    public boolean deleteArticle(int id);
    public List<Article> searchArticleByName(String name);
    public Page<Article> displayArticleByPage(int numberPage, int limit);
}
