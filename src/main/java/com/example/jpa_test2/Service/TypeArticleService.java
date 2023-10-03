package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.TypeArticle;
import com.example.jpa_test2.Model.TypeCourse;
import com.example.jpa_test2.Repository.TypeArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TypeArticleService implements ITypeArticle{

    @Autowired
    private TypeArticleRepository typeArticleRepo;
    @Override
    public boolean addTypeArticle(TypeArticle typeArticle) {
        if(! typeArticleRepo.existsById(typeArticle.getTypeArticleId())){
            typeArticleRepo.save(typeArticle);
            return true;
        }
        return false;
    }

    @Override
    public boolean reviseTypeArticle(TypeArticle typeArticle) {
        TypeArticle typeArticle1 = typeArticleRepo.findById(typeArticle.getTypeArticleId()).orElse(null);
        if(typeArticle1 != null){
            typeArticle1 = typeArticle;
            typeArticleRepo.save(typeArticle1);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeTypeArticle(int typeArticleId) {
        if(typeArticleRepo.existsById(typeArticleId)){
            typeArticleRepo.deleteById(typeArticleId);
            return true;
        }
        return false;
    }

    @Override
    public Page<TypeArticle> findTypeArticleByPage(int pageNumber, int limit) {
        Pageable pageable = PageRequest.of(pageNumber-1, limit);
        return typeArticleRepo.findAll(pageable);
    }
}
