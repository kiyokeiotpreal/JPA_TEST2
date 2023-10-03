package com.example.jpa_test2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    private String userName;

    private String accountName;

    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permissionId", foreignKey = @ForeignKey(name = "fk_Permission_Account"))
    @JsonManagedReference
    private Permission permission;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    @JsonBackReference
    private Set<RegisterCourse> registerCourseSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    @JsonBackReference
    private Set<Article> articleSet;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Set<RegisterCourse> getRegisterCourseSet() {
        return registerCourseSet;
    }

    public void setRegisterCourseSet(Set<RegisterCourse> registerCourseSet) {
        this.registerCourseSet = registerCourseSet;
    }

    public Set<Article> getArticleSet() {
        return articleSet;
    }

    public void setArticleSet(Set<Article> articleSet) {
        this.articleSet = articleSet;
    }
}
