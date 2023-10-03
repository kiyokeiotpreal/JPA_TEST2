package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Account;
import com.example.jpa_test2.Model.Article;
import com.example.jpa_test2.Model.RegisterCourse;
import com.example.jpa_test2.Repository.AccountRepository;
import com.example.jpa_test2.Repository.ArticleRepository;
import com.example.jpa_test2.Repository.CourseRepository;
import com.example.jpa_test2.Repository.RegisterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccounts{
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private RegisterCourseRepository registerCourseRepo;
    @Autowired
    private ArticleRepository articleRepo;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepo;
    @Override
    public boolean addAccount(Account account) {
        if(!accountRepo.existsById(account.getAccountId()) && checkPassword(account.getPassword())){
            for (Account account1: accountRepo.findAll()) {
                if(account1.getAccountName().equals(account.getAccountName())){
                    return false;
                }
            }
            accountRepo.save(account);
            return true;
        }
        return false;
    }

    private boolean checkPassword(String password) {
        boolean check1 = false, check2 = false, check3=false;
        char []arr = password.toCharArray();
        for (char c : arr) {
            if(Character.isDigit(c)){
                check1 = true;
            }
            if (Character.isLetter(c)){
                check2 = true;
            }
            if(!Character.isLetter(c) && !Character.isDigit(c)){
                check3 = true;
            }
        }
        return check1 && check2 && check3;
    }

    @Override
    public boolean reviseAccount(Account account) {
        Account accountRevise = accountRepo.findById(account.getAccountId()).orElse(null);
        if(accountRevise != null){
            for (Account account1: accountRepo.findAll()) {
                if(account1.getAccountId() != account.getAccountId() && account1.getAccountName().equals(account.getAccountName())){
                    return false;
                }
            }
            accountRevise = account;
            if(checkPassword(accountRevise.getPassword())){
                accountRepo.save(accountRevise);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAccount(int accountId) {
        if(accountRepo.existsById(accountId)){
            for (RegisterCourse registerCourse: registerCourseRepo.findAll()) {
                if(registerCourse.getAccount().getAccountId()== accountId){
                    int courseId = registerCourse.getCourse().getCourseId();
                    registerCourseRepo.delete(registerCourse);
                    courseService.updateCourse(courseRepo.findById(courseId).get());
                }
            }
            for (Article article: articleRepo.findAll()) {
                if(article.getAccount().getAccountId()==accountId){
                    articleRepo.delete(article);
                }
            }
            accountRepo.deleteById(accountId);
            return true;
        }
        return false;
    }

    @Override
    public Account findAccountByName(String name) {
        return null;
    }

    @Override
    public Page<Account> findAccountByPage(int pageNumber, int limit) {
        Pageable pageable = PageRequest.of(pageNumber-1, limit);
        return accountRepo.findAll(pageable);
    }
}
