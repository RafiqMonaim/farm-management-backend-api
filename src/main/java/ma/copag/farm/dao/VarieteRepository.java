package ma.copag.farm.dao;

import ma.copag.farm.entities.Variete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")
public interface VarieteRepository extends JpaRepository<Variete,Long> {
}
