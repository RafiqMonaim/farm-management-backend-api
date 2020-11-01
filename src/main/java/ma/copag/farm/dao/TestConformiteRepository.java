package ma.copag.farm.dao;

import ma.copag.farm.entities.Parcelle;
import ma.copag.farm.entities.TestConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")
public interface TestConformiteRepository extends JpaRepository<TestConformite,Long> {

    public List<TestConformite> findByParcelle(Parcelle parcelle);

    public List<TestConformite> findByParcelleId(Long id);

    public List<TestConformite> findByDateTestBetween(Date date,Date datee);


}
