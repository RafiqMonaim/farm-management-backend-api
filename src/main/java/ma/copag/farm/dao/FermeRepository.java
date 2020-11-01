package ma.copag.farm.dao;

import ma.copag.farm.entities.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface FermeRepository extends JpaRepository<Ferme,Long> {
}
