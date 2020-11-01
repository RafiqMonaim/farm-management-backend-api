package ma.copag.farm.dao;

import ma.copag.farm.entities.Parcelle;
import ma.copag.farm.entities.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")
public interface ParcelleRepository extends JpaRepository<Parcelle,Long> {
    //public List<Parcelle> findBySecteurNom(String nom);

    public Parcelle findBySecteurAndAndId(Secteur secteur, Long id);

    public List<Parcelle> findBySecteur(Secteur secteur);

}
