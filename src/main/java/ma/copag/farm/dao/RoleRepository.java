package ma.copag.farm.dao;

import ma.copag.farm.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("http://localhost:4200")
public interface RoleRepository extends JpaRepository<AppRole,Long> {

    public AppRole findByRoleName(String roleName);

    //public List<AppRole> findByRoleName(String roleName);
}
