package ma.copag.farm.dao;

import ma.copag.farm.entities.AppRole;
import ma.copag.farm.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("http://localhost:4200")
public interface UserRepository extends JpaRepository<AppUser,Long> {

    public AppUser findByEmail(String email);

    public List<AppUser> findByRolesNull();

    public List<AppUser> findByRolesIsNotNull();

    public List<AppUser> findByRolesIsNotNullAndRolesNotLike(AppRole role);
}
