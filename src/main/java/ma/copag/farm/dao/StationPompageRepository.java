package ma.copag.farm.dao;

import ma.copag.farm.entities.Ferme;
import ma.copag.farm.entities.StationPompage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")
public interface StationPompageRepository extends JpaRepository<StationPompage,Long> {

    public List<StationPompage> findByFerme(Ferme ferme);
}
