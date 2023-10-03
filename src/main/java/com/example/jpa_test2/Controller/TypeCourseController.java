package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.Course;
import com.example.jpa_test2.Model.TypeCourse;
import com.example.jpa_test2.Service.TypeCourseService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class TypeCourseController {
    @Autowired
    private TypeCourseService typeCourseService;

    @RequestMapping(value = "addTypeCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addTypeCourse(@RequestBody String typeCourse1){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        TypeCourse typeCource = gson.fromJson(typeCourse1, TypeCourse.class);
        boolean isCheck = typeCourseService.addTypeCourse(typeCource);
        if (isCheck) {
            return "Add success";
        }
        return "Add failed";
    }

    @RequestMapping(value = "reviseTypeCourse", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String reviseTypeCourse(@RequestBody String typeCourse1){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        TypeCourse typeCource = gson.fromJson(typeCourse1, TypeCourse.class);
        boolean isCheck = typeCourseService.reviseTypeCourse(typeCource);
        if (isCheck) {
            return "Revise success";
        }
        return "Revise failed";
    }

    @RequestMapping(value = "deleteTypeCourse", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteTypeCourse(@RequestParam int typeCourseId){
        boolean isCheck =  typeCourseService.removeTypeCourse(typeCourseId);
        if(isCheck) {
            return "delete success";
        }
        return "delete failed";
    }
}
