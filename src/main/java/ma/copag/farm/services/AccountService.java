package ma.copag.farm.services;

import ma.copag.farm.entities.AppRole;
import ma.copag.farm.entities.AppUser;

import java.util.List;

public interface AccountService {
    public AppUser saveUser(AppUser user);
    public AppRole saveRole(AppRole role);

    public void addRoleToUser(String email, String roleName);

    public AppUser findUserByEmail(String email);
    public AppUser findUserById(Long id);

    public List<AppUser> findUserByRoleNull();

    public List<AppUser> findAllUsers();

    //public List<AppUser> findUsers();
}
