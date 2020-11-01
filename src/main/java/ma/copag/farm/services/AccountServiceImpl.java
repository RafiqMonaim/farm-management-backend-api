package ma.copag.farm.services;

import ma.copag.farm.dao.RoleRepository;
import ma.copag.farm.dao.UserRepository;
import ma.copag.farm.entities.AppRole;
import ma.copag.farm.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;//hachage de password

    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        AppUser user = userRepository.findByEmail(email);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public AppUser findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<AppUser> findUserByRoleNull() {
        return userRepository.findByRolesNull();
    }

    @Override
    public List<AppUser> findAllUsers() {
        return userRepository.findByRolesIsNotNullAndRolesNotLike(roleRepository.findByRoleName("ADMIN"));
    }

}
