package ma.copag.farm.web;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ma.copag.farm.dao.RoleRepository;
import ma.copag.farm.dao.UserRepository;
import ma.copag.farm.entities.AppRole;
import ma.copag.farm.entities.AppUser;
import ma.copag.farm.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterForm userForm){
        if (!userForm.getPassword().equals(userForm.getRepassword()))
            throw new RuntimeException("Confirmez votre mot de passe !");
        AppUser userexist =  accountService.findUserByEmail(userForm.getEmail());
        if(userexist!=null) throw new RuntimeException("Cet email est déja utiliser !");
        AppUser user = new AppUser();
        user.setNom(userForm.getNom());
        user.setPrenom(userForm.getPrenom());
        user.setMatricule(userForm.getMatricule());
        user.setTelephone(userForm.getTelephone());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        return accountService.saveUser(user);
    }

    @GetMapping("/users")
    public List<AppUser> getOperateurs(){
        return this.accountService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public AppUser getOperateur(@PathVariable("id") Long id){
        return accountService.findUserById(id);
    }

    @GetMapping("/registers")
    public List<AppUser> getRegistration(){
        return this.accountService.findUserByRoleNull();
    }

    @GetMapping("/registers/{id}")
    public AppUser getRegistredUser(@PathVariable("id") Long id){
        return accountService.findUserById(id);
    }

    @PutMapping(value = "/registers/{id}")
    public AppUser confirmeRegistration(@PathVariable("id") Long id,@RequestParam("roleName") String roleName ){
        AppUser user = accountService.findUserById(id);
        user.getRoles().add(roleRepository.findByRoleName(roleName));
        //accountService.addRoleToUser(user.getEmail(),role);
        return userRepository.save(user);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public AppUser currentUser(Authentication authentication) {
        AppUser user = userRepository.findByEmail(authentication.getName());
        return user;
    }
}
@Data
class RegisterForm {
    private Long id;
    private String nom,prenom,matricule,telephone;
    private String email;
    private String password;
    private String repassword;
}
