package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.StudentStatus;
import com.example.jpa_test2.Model.TypeCourse;
import com.example.jpa_test2.Service.StudentStatusService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class StudentStatusController {
    @Autowired
    private StudentStatusService studentStatusService;

    @RequestMapping(value = "addStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addStatus(@RequestBody String status){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        StudentStatus studentStatus = gson.fromJson(status, StudentStatus.class);
        boolean isCheck = studentStatusService.addStatus(studentStatus);
        if (isCheck) {
            return "Add success";
        }
        return "Add failed";
    }

    @RequestMapping(value = "reviseStatus", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> reviseStatus(@RequestBody String status){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<>() {
            @Override
            public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
            }
        }).create();

        StudentStatus studentStatus = gson.fromJson(status, StudentStatus.class);
        boolean isCheck = studentStatusService.reviseStatus(studentStatus);
        if(isCheck) {
            return new ResponseEntity<>("revise success", HttpStatus.OK);
        }
        return new ResponseEntity<>("revise failed", HttpStatus.OK);
    }

    @RequestMapping(value = "deleteStatus", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteStatus(@RequestParam int statusId){
        boolean isCheck = studentStatusService.deleteStatus(statusId);
        if(isCheck){
            return "delete success";
        }
        return "delete failed";
    }

}
