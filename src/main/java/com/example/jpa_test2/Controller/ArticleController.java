package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.Article;
import com.example.jpa_test2.Service.ArticleService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping(value = "add/new/article", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addNewArticle(@RequestBody String article){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Article article1 = gson.fromJson(article, Article.class);
        boolean check = articleService.addNewArticle(article1);
        if(check){
            return "them thanh cong";
        }
        return "them that bai";
    }
    @RequestMapping(value = "update/article", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateArticle(@RequestBody String article){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Article article1 = gson.fromJson(article,Article.class);
        boolean check = articleService.updateArticle(article1);
        if(check){
            return "sua thanh cong";
        }
        return "sua that bai";
    }
    @RequestMapping(value = "delete/article", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteArticle(@RequestParam int id) {
        boolean check = articleService.deleteArticle(id);
        if(check){
            return "xoa thanh cong";
        }
        return "xoa that bai";
    }
    @RequestMapping(value = "display/article/byName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> displayArticleByName(@RequestParam String nameArticle) {
        return articleService.searchArticleByName(nameArticle);
    }
    @RequestMapping(value = "get/article/byPage", method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Article> getArticleByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                          @RequestParam (defaultValue = "2") int limit){
        return articleService.displayArticleByPage(pagenumber,limit);
    }
}
