package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.TypeArticle;
import com.example.jpa_test2.Model.TypeCourse;
import org.springframework.data.domain.Page;

public interface ITypeArticle {
    public boolean addTypeArticle(TypeArticle typeArticle);
    public boolean reviseTypeArticle(TypeArticle typeArticle);
    public boolean removeTypeArticle(int typeArticleId);
    Page<TypeArticle> findTypeArticleByPage(int pageNumber, int limit);
}
