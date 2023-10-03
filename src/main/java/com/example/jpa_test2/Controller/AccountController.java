package com.example.jpa_test2.Controller;

import com.example.jpa_test2.Model.Account;
import com.example.jpa_test2.Model.Course;
import com.example.jpa_test2.Model.Permission;
import com.example.jpa_test2.Model.Topic;
import com.example.jpa_test2.Service.AccountService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "addAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addAccount(@RequestBody String account){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Account account1 = gson.fromJson(account, Account.class);
        boolean isCheck = accountService.addAccount(account1);
        if (isCheck) {
            return "Add success";
        }
        return "Add failed";
    }

    @RequestMapping(value = "reviseAccount", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String reviseAccount(@RequestBody String account){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Account account1 = gson.fromJson(account, Account.class);
        boolean isCheck = accountService.reviseAccount(account1);
        if (isCheck) {
            return "Revise success";
        }
        return "Revise failed";
    }

    @RequestMapping(value = "deleteAccount", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteAccount(@RequestParam int accountId){
        boolean isCheck =  accountService.deleteAccount(accountId);
        if(isCheck) {
            return "delete success";
        }
        return "delete failed";
    }

    @RequestMapping(value = "display/account/by/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Account> displayAccountByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                          @RequestParam (defaultValue = "2") int limit){
        return accountService.findAccountByPage(pagenumber,limit);
    }
}
