package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Account;
import com.example.jpa_test2.Model.Article;
import com.example.jpa_test2.Model.Topic;
import com.example.jpa_test2.Repository.AccountRepository;
import com.example.jpa_test2.Repository.ArticleRepository;
import com.example.jpa_test2.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class ArticleService implements IArticle{

    @Autowired
    private TopicRepository topicRepo;
    @Autowired
    private ArticleRepository articleRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Override
    public boolean addNewArticle(Article article) {
        Optional<Topic> topicOptional = topicRepo.findById(article.getTopic().getTopicId());
        Optional<Account> accountOptional = accountRepo.findById(article.getAccount().getAccountId());
        if(!articleRepo.existsById(article.getArticleId())){
            if(topicOptional.isPresent() && accountOptional.isPresent()){
                LocalDateTime localDateTime = LocalDateTime.now();
                LocalDate creatTime = localDateTime.toLocalDate();
                article.setCreateDate(creatTime);
                articleRepo.save(article);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateArticle(Article article) {
        Optional<Topic> topicOptional = topicRepo.findById(article.getTopic().getTopicId());
        Optional<Account> accountOptional = accountRepo.findById(article.getAccount().getAccountId());
        if(articleRepo.existsById(article.getArticleId())){
            if(topicOptional.isPresent() && accountOptional.isPresent()){
                LocalDateTime localDateTime = LocalDateTime.now();
                LocalDate creatTime = localDateTime.toLocalDate();
                article.setCreateDate(creatTime);
                articleRepo.save(article);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteArticle(int id) {
        if(articleRepo.existsById(id)){
            articleRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Article> searchArticleByName(String name) {
        return articleRepo.findByArticleName(name);
    }

    @Override
    public Page<Article> displayArticleByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return articleRepo.findAll(pageable);
    }
}
