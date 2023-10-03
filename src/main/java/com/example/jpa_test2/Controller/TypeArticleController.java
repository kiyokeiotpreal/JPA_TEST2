package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.TypeArticle;
import com.example.jpa_test2.Model.TypeCourse;
import com.example.jpa_test2.Service.TypeArticleService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class TypeArticleController {
    @Autowired
    private TypeArticleService typeArticleService;

    @RequestMapping(value = "addTypeArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addTypeArticle(@RequestBody String typeArticle){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        TypeArticle typeArticle1 = gson.fromJson(typeArticle, TypeArticle.class);
        boolean isCheck = typeArticleService.addTypeArticle(typeArticle1);
        if (isCheck) {
            return "Add success";
        }
        return "Add failed";
    }

    @RequestMapping(value = "reviseTypeArticel", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String reviseTypeArticle(@RequestBody String typeArticle){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        TypeArticle typeArticle1 = gson.fromJson(typeArticle, TypeArticle.class);
        boolean isCheck = typeArticleService.reviseTypeArticle(typeArticle1);
        if (isCheck) {
            return "Revise success";
        }
        return "Revise failed";
    }

    @RequestMapping(value = "deleteTypeArticle", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteTypeArticle(@RequestParam int typeArticleId){
        boolean isCheck =  typeArticleService.removeTypeArticle(typeArticleId);
        if(isCheck) {
            return "delete success";
        }
        return "delete failed";
    }
}
