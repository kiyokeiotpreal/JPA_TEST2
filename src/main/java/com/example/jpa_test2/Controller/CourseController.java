package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.Course;
import com.example.jpa_test2.Model.TypeCourse;
import com.example.jpa_test2.Service.CourseService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "addCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addTypeCourse(@RequestBody String course1){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Course cource = gson.fromJson(course1, Course.class);
        boolean isCheck = courseService.addCourse(cource);
        if (isCheck) {
            return "Add success";
        }
        return "Add failed";
    }

    @RequestMapping(value = "reviseCourse", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String reviseTypeCourse(@RequestBody String course1){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Course cource = gson.fromJson(course1, Course.class);
        boolean isCheck = courseService.reviseCourse(cource);
        if (isCheck) {
            return "Revise success";
        }
        return "Revise failed";
    }

    @RequestMapping(value = "deleteCourse", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteTypeCourse(@RequestParam int courseId){
        boolean isCheck =  courseService.removeCourse(courseId);
        if(isCheck) {
            return "delete success";
        }
        return "delete failed";
    }

    @RequestMapping(value = "getCourseByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> findCourseByName(@RequestParam String courseName){
        return courseService.findCourse(courseName);
    }
}
