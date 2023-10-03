package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.Permission;
import com.example.jpa_test2.Model.RegisterCourse;
import com.example.jpa_test2.Model.Student;
import com.example.jpa_test2.Model.Topic;
import com.example.jpa_test2.Service.RegisterCourseService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class RegisterCourseController {

    @Autowired
    private RegisterCourseService registerCourseService;

    @RequestMapping(value = "addRegisterCource", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addRegisterCourse(@RequestBody String registerCourse){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        RegisterCourse registerCourse1 = gson.fromJson(registerCourse, RegisterCourse.class);
        boolean isCheck = registerCourseService.addRegisterCourse(registerCourse1);
        if(isCheck) {
            return "add sucess";
        }
        return "add failed";
    }

    @RequestMapping(value = "reviseRegisterCourse", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String reviseRegisterCourse(@RequestBody String registerCourse){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        RegisterCourse registerCourse1 = gson.fromJson(registerCourse, RegisterCourse.class);
        boolean isCheck = registerCourseService.reviseRegisterCourse(registerCourse1);
        if (isCheck) {
            return "Revise success";
        }
        return "Revise failed";
    }

    @RequestMapping(value = "display/registercourse/by/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<RegisterCourse> displayRegisterCourseByPage(@RequestParam(defaultValue = "0") int pagenumber,
                                          @RequestParam (defaultValue = "2") int limit){
        return registerCourseService.findRegisterCourseBypage(pagenumber,limit);
    }
}
