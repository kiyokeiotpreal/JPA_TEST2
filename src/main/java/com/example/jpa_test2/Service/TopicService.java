package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Article;
import com.example.jpa_test2.Model.Topic;
import com.example.jpa_test2.Repository.ArticleRepository;
import com.example.jpa_test2.Repository.TopicRepository;
import com.example.jpa_test2.Repository.TypeArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService implements ITopic{

    @Autowired
    private TopicRepository topicRepo;
    @Autowired
    private TypeArticleRepository articleTypeRepo;
    @Autowired
    private ArticleRepository articleRepo;

    @Override
    public boolean updateTopic(Topic topic) {
        if(topicRepo.existsById(topic.getTopicId())){
            if(articleTypeRepo.existsById(topic.getTypearticle().getTypeArticleId())) {
                topicRepo.save(topic);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addNewTopic(Topic topic) {
        if(!topicRepo.existsById(topic.getTopicId())) {
            if (articleTypeRepo.existsById(topic.getTypearticle().getTypeArticleId())) {
                topicRepo.save(topic);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteTopic(int id) {
        Topic topic = topicRepo.findById(id).orElse(null);
        if(topicRepo.existsById(id)) {
            for (Article article : articleRepo.findAll()) {
                if (article.getTopic().getTopicId() == id) {
                    articleRepo.delete(article);
                }
            }
            topicRepo.delete(topic);
            return true;
        }
        return false;
    }

    @Override
    public Page<Topic> displayTopicByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return topicRepo.findAll(pageable);
    }
}
