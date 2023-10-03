package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Topic;
import org.springframework.data.domain.Page;

public interface ITopic {
    public boolean addNewTopic(Topic topic);
    public boolean updateTopic(Topic topic);
    public boolean deleteTopic(int id);
    public Page<Topic> displayTopicByPage(int numberPage, int limit);
}
