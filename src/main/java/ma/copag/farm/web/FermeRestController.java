package ma.copag.farm.web;

import lombok.Data;
import ma.copag.farm.dao.ParcelleRepository;
import ma.copag.farm.dao.SecteurRepository;
import ma.copag.farm.dao.TestConformiteRepository;
import ma.copag.farm.entities.Parcelle;
import ma.copag.farm.entities.TestConformite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.*;

@RestController
public class FermeRestController {

    @Autowired
    private ParcelleRepository parcelleRepository;
    @Autowired
    private TestConformiteRepository ConformiteRepository;

    @PostMapping("/testConformes")
    public TestConformite saveTast(@RequestBody TestForm testForm){
        TestConformite testConformite = new TestConformite();
        testConformite.setDateTest(testForm.getDate());
        Parcelle parcelle = parcelleRepository.findById(testForm.getIdParcelle()).get();
        testConformite.setParcelle(parcelle);
        List<Double> goutteurs = testForm.getGoutteurs();
        double somme=0, moymin=1, moymax=1;
        Collections.sort(goutteurs);
        for (int i = 0; i < goutteurs.size() ; i++) {
            somme = somme+ goutteurs.get(i);
        }
        moymax = somme/goutteurs.size();
        double sommemin=0;
        for (int i = 0; i < (int)(goutteurs.size()*(25/100)) ; i++) {
            sommemin = sommemin + goutteurs.get(i);
        }
        moymin = sommemin/(int)(goutteurs.size()*(25/100));
        testConformite.setDebitMoy(moymax);
        testConformite.setDebitMin(moymin);
        testConformite.setCoefficienUniform((moymin/moymax)*100);
        if (moymin/moymax >= 0.9  ){
            testConformite.setDescription("Test bien Conforme"); }
        else { testConformite.setDescription("Test Non Conforme "); }
        ConformiteRepository.save(testConformite);
        return testConformite;
    }

   /* public TestConformite ConformeTest(@RequestBody TestForm testForm){
        double somme=0, moymin, moymax;
        TestConformite testConformite = new TestConformite();
        List<Double> goutteurs = testForm.getGoutteurs();
        testConformite.setGoutteurs(goutteurs);
        Collections.sort(goutteurs);
        for (int i = 0; i < goutteurs.size() ; i++) {
            somme = somme+ goutteurs.get(i);
        }
        moymax = somme/goutteurs.size();
        double sommemin=0;
        for (int i = 0; i < (int)(goutteurs.size()*(25/100)) ; i++) {
            sommemin = sommemin + goutteurs.get(i);
        }
        moymin = sommemin/(int)(goutteurs.size()*(25/100));
        testConformite.setDateTest(testForm.getDate());
        //testConformite.setSecteur(secteurRepository.findById(testForm.getIdSecteur()).get());
        testConformite.setParcelle(parcelleRepository.findById(testForm.getIdParcelle()).get());
        testConformite.setDebitMoy(moymax);
        testConformite.setDebitMin(moymin);
        testConformite.setCoefficienUniform((moymin/moymax)*100);
        if (moymin/moymax >= 0.9  ){
            testConformite.setDescription("Test bien Conforme"); }
        else { testConformite.setDescription("Test Non Conforme "); }
        TestConformite test = ConformiteRepository.save(testConformite);
        return testConformite;
    }
*/
}

@Data
class TestForm {
    @Temporal(TemporalType.DATE)
    private Date date;
    private Long idParcelle;
    private List<Double> goutteurs = new ArrayList<>();
}
