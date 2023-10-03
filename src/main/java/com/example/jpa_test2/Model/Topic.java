package com.example.jpa_test2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicId;

    private String topicName;

    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    @JsonBackReference
    private Set<Article> articleSet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeArticleId", foreignKey = @ForeignKey(name = "fk_TypeArticle_Topic"))
    @JsonManagedReference
    private TypeArticle typearticle;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Article> getArticleSet() {
        return articleSet;
    }

    public void setArticleSet(Set<Article> articleSet) {
        this.articleSet = articleSet;
    }

    public TypeArticle getTypearticle() {
        return typearticle;
    }

    public void setTypearticle(TypeArticle typearticle) {
        this.typearticle = typearticle;
    }
}
