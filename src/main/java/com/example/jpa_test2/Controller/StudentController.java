package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.Course;
import com.example.jpa_test2.Model.Student;
import com.example.jpa_test2.Service.StudentService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "addStudent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addStudent(@RequestBody String student){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Student student1 = gson.fromJson(student, Student.class);
        boolean isCheck = studentService.addStudent(student1);
        if (isCheck) {
            return "Add success";
        }
        return "Add failed";
    }

    @RequestMapping(value = "reviseStudent", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String reviseStudent(@RequestBody String student){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Student student1 = gson.fromJson(student, Student.class);
        boolean isCheck = studentService.reviseStudent(student1);
        if (isCheck) {
            return "Revise success";
        }
        return "Revise failed";
    }

    @RequestMapping(value = "deleteStudent", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteStudent(@RequestParam int studentId){
        boolean isCheck =  studentService.deleteStudent(studentId);
        if(isCheck) {
            return "delete success";
        }
        return "delete failed";
    }
}
