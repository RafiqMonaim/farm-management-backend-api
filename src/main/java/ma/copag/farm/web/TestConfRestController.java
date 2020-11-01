package ma.copag.farm.web;

import ma.copag.farm.dao.ParcelleRepository;
import ma.copag.farm.dao.TestConformiteRepository;
import ma.copag.farm.entities.TestConformite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

//@RestController
public class TestConfRestController {

/*    @Autowired
    private TestConformiteRepository conformiteRepository;
    @Autowired
    private ParcelleRepository parcelleRepository;*/

   /* @GetMapping("/parcelles/{id}/testConformites")
    public List<TestConformite> getTests(@PathVariable("id") Long id){
        return conformiteRepository.findByParcelleId(id);
    }

    @GetMapping("/parcelles/{id}/testConformites/{idTest}")
    public TestConformite getTest(@PathVariable("idTest") Long id){

        return conformiteRepository.findById(id).get();
    }*/
/*
    @PostMapping(value = "/parcelles/{id}/testConformites")
    public TestConformite addTest(@PathVariable("id") Long id,
                                  @RequestBody TestConformite testConformite){
        double somme=0, moymin, moymax;
        TestConformite testConf = new TestConformite();
        testConf.setDateTest(testConformite.getDateTest());
        testConf.setGoutteurs(testConformite.getGoutteurs());
        testConf.setParcelle(parcelleRepository.findById(id).get());
        Collections.sort(testConf.getGoutteurs());
        int goutteurSize = testConformite.getGoutteurs().size();
        for (int i = 0; i < goutteurSize ; i++) {
            somme = somme+ testConf.getGoutteurs().get(i);
        }
        moymax = somme/testConf.getGoutteurs().size();
        double sommemin=0;
        for (int i = 0; i < (int)(goutteurSize*(25/100)) ; i++) {
            sommemin = sommemin + testConf.getGoutteurs().get(i);
        }
        moymin = sommemin/(int)(goutteurSize*(25/100));
        testConf.setDebitMoy(moymax);
        testConf.setDebitMin(moymin);
        testConf.setCoefficienUniform((moymin/moymax)*100);
        if (moymin/moymax >= 0.9  ){
            testConf.setDescription("Test bien Conforme"); }
        else { testConf.setDescription("Test Non Conforme "); }
        TestConformite test = conformiteRepository.save(testConf);
        return test;
    }*/

    /*@DeleteMapping("/parcelles/{id}/testConformites/{idTest}")
    public void deleteTest(@PathVariable("idTest") Long id ){
        conformiteRepository.deleteById(id);
    }
*/

}
