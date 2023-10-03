package com.example.jpa_test2.Repository;

import com.example.jpa_test2.Model.TypeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeArticleRepository extends JpaRepository<TypeArticle, Integer> {
}
