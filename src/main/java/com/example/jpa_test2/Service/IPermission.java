package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Permission;

public interface IPermission {
    public boolean addPermission(Permission permission);
    public boolean revisePermission(Permission permission);
    public boolean deletePermission(int permissionId);

}
