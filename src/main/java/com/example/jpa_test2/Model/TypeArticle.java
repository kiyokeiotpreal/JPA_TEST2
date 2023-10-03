package com.example.jpa_test2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class TypeArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeArticleId;

    private String articleName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typearticle")
    @JsonBackReference
    private Set<Topic> topicSet;

    public int getTypeArticleId() {
        return typeArticleId;
    }

    public void setTypeArticleId(int typeArticleId) {
        this.typeArticleId = typeArticleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Set<Topic> getTopicSet() {
        return topicSet;
    }

    public void setTopicSet(Set<Topic> topicSet) {
        this.topicSet = topicSet;
    }
}
