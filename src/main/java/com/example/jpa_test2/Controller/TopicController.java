package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.Topic;
import com.example.jpa_test2.Service.TopicService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;
    @RequestMapping(value = "add/new/Topic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addNewCourse(@RequestBody String topic){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Topic topic1 = gson.fromJson(topic, Topic.class);
        boolean check = topicService.addNewTopic(topic1);
        if(check){
            return "them thanh cong";
        }
        return "them that bai";
    }
    @RequestMapping(value = "update/topic", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCourse(@RequestBody String toPic){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Topic topic1 = gson.fromJson(toPic, Topic.class);
        boolean check = topicService.updateTopic(topic1);
        if(check){
            return "sua thanh cong";
        }
        return "sua that bai";
    }
    @RequestMapping(value = "delete/topic", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCourse(@RequestParam int id) {
        boolean check = topicService.deleteTopic(id);
        if(check){
            return "xoa thanh cong";
        }
        return "xoa that bai";
    }
    @RequestMapping(value = "display/topic/by/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Topic> displayTopicByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                          @RequestParam (defaultValue = "2") int limit){
        return topicService.displayTopicByPage(pagenumber,limit);
    }
}
