package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.*;
import com.example.jpa_test2.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermission{
    @Autowired
    private PermissionRepository permissionRepo;

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
    public boolean addPermission(Permission permission) {
        if(! permissionRepo.existsById(permission.getPermissionId())){
            permissionRepo.save(permission);
            return true;
        }
        return false;
    }

    @Override
    public boolean revisePermission(Permission permission) {
        Permission permission1 = permissionRepo.findById(permission.getPermissionId()).orElse(null);
        if(permission1 != null){
            permission1 = permission;
            permissionRepo.save(permission1);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePermission(int permissionId) {
        if(permissionRepo.existsById(permissionId)){
            for (Account account: accountRepo.findAll()) {
                if(account.getPermission().getPermissionId()==permissionId){
                    for (RegisterCourse registerCourse: registerCourseRepo.findAll()) {
                        if(registerCourse.getAccount().getAccountId()==account.getAccountId()){
                            int courseId = registerCourse.getCourse().getCourseId();
                            registerCourseRepo.delete(registerCourse);
                            courseService.updateCourse(courseRepo.findById(courseId).get());
                        }
                    }
                    for (Article article: articleRepo.findAll()) {
                        if(article.getAccount().getAccountId()==account.getAccountId()){
                            articleRepo.delete(article);
                        }
                    }
                    accountRepo.delete(account);
                }
            }
            permissionRepo.deleteById(permissionId);
            return true;
        }
        return false;
    }
}
