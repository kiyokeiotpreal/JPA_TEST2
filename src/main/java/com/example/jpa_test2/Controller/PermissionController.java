package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.Permission;
import com.example.jpa_test2.Model.TypeCourse;
import com.example.jpa_test2.Service.PermissionService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "addPermission", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addPermission(@RequestBody String permission){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Permission permission1 = gson.fromJson(permission, Permission.class);
        boolean isCheck = permissionService.addPermission(permission1);
        if (isCheck) {
            return "Add success";
        }
        return "Add failed";
    }

    @RequestMapping(value = "revisePermission", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String revisePermission(@RequestBody String permission){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Permission permission1 = gson.fromJson(permission, Permission.class);
        boolean isCheck = permissionService.revisePermission(permission1);
        if (isCheck) {
            return "Revise success";
        }
        return "Revise failed";
    }

    @RequestMapping(value = "deletePermission", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePermission(@RequestParam int permissionId){
        boolean isCheck = permissionService.deletePermission(permissionId);
        if(isCheck){
            return "delete success";
        }
        return "delete failed";
    }
}
