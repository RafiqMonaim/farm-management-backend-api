package ma.copag.farm.service;

import ma.copag.farm.dao.*;
import ma.copag.farm.entities.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Stream;
@Service
@Transactional
public class FermeServiceImpl implements IFermeInitService {

    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private FermeRepository fermeRepository;
    @Autowired
    private SecteurRepository secteurRepository;
    @Autowired
    private ParcelleRepository parcelleRepository;
    @Autowired
    private StationPompageRepository pompageRepository;
    @Autowired
    private PorteGreffeRepository greffeRepository;
    @Autowired
    private VarieteRepository varieteRepository;
    @Autowired
    private GroupeVarieteRepository groupeVarieteRepository;
    @Autowired
    private ApportEauRepository eauRepository;
    @Autowired
    private TestConformiteRepository conformiteRepository;


    @Override
    public void initVille() {

        Stream.of("Taroudant","Agadir","Aoulouz","Oulad Teima","Sebt Gerdan").forEach(nom->{
            Ville ville = new Ville();
            ville.setNom(nom);
            ville.setRegion("Sousse-Massa");
            villeRepository.save(ville);
        });

    }

    @Override
    public void initFerme() {
        String[] proprietaires = new String[] {"Ahmed Amouri", "Fatima Ararabi", "Molay Ahmed Skalli", "Copag Agrums","Safia Nourri"};
        villeRepository.findAll().forEach(ville -> {
            Stream.of("Domaine Albora 1","Domaine Citronade","Domaine Onrangier","Domaine Lemoune 1","Domaine Albora 2")
                    .forEach(nom->{
                        Ferme ferme = new Ferme();
                        ferme.setName(nom);
                        ferme.setProprietaire(proprietaires[new Random().nextInt(proprietaires.length)]);
                        ferme.setContenance(9+(float)(Math.random()*20));
                        ferme.setNbrStationPompage(2+(int)(Math.random()*4));
                        ferme.setNbrSecteur(5+(int)(Math.random()*15));
                        ferme.setVille(ville);
                        ferme.setReference(ville.getNom()+nom);
                        ferme.setNbrArbre(5000+(int)(Math.random()*5000));
                        fermeRepository.save(ferme);
            });
        });
    }


    @Override
    public void initSecteur() {
        double[] doseGoutteur = new double[] {4.00, 4.50,5.00, 5.50,6.00, 6.50,7.00, 7.50, 8.00, 8.50, 9.00, 9.50,10.00};
        fermeRepository.findAll().forEach(ferme -> {
                for (int i = 0; i <ferme.getNbrSecteur() ; i++) {
                    Secteur secteur = new Secteur();
                    secteur.setNom("Secteur"+(i+1));
                    secteur.setFerme(ferme);
                    secteur.setDebitGoutteur(doseGoutteur[new Random().nextInt(doseGoutteur.length)]);
                    secteur.setNbrParcelle(4+(int)(Math.random()*8));
                    secteur.setNbrArbre((int)(Math.random()*ferme.getNbrArbre()));
                    secteur.setNbrGouteurs(10000+(int)(Math.random()*1000));
                    secteur.setPluviometrieSys(600+Math.random()*100);
                    secteurRepository.save(secteur);
                }
            });
    }

    @Override
    public void initPompeStation() {
        fermeRepository.findAll().forEach(ferme -> {
            for (int i = 0; i < ferme.getNbrStationPompage() ; i++) {
                StationPompage stationPompage = new StationPompage();
                stationPompage.setNom("Station "+(i+1));
                stationPompage.setType("local");
                stationPompage.setFerme(ferme);
                stationPompage.setPuissance(40 + Math.random() * 100);
                pompageRepository.save(stationPompage);
            }
        });

    }

    @Override
    public void initGroupVariete() {
        //int[] nbr = new int[] {1,2,3,4};
        Stream.of("ORANGES","PETITS FRUITS","MANDARINIER")
                .forEach(groupe ->{
                    GroupeVariete groupeVariete = new GroupeVariete();
                    groupeVariete.setNomGroupe(groupe);
                    groupeVariete.setType("AGRUMES");
                    groupeVarieteRepository.save(groupeVariete);
                });

    }

    @Override
    @Transactional
    public void initVariete() {
        groupeVarieteRepository.findAll().forEach(groupeVariete -> {
            Stream.of("Navels", "Maroc Late", "Sanguines", "Nova")
                    .forEach(nom -> {
                        Variete variete = new Variete();
                        variete.setVariete(nom);
                        variete.setGroupeVariete(groupeVariete);
                        varieteRepository.save(variete);
                    });
        });
    }

    @Override
    public void initPortGreffe() {
        List<Parcelle> parcelles = parcelleRepository.findAll();
        String[] descript = new String[] {"dominant", "le plus utilisé", "sensibilité à la Tristeza","Bon enracinement traçant et pivotant","Faible vigueur des arbres"};
        Stream.of("Bigaradier Citrus aurantium","Poncirus trifoliata","Citrange troyer","volkameriana")
                .forEach(greffe ->{
                    PorteGreffe porteGreffe = new PorteGreffe();
                    porteGreffe.setNom(greffe);
                    porteGreffe.setParcelle(parcelles.get(new Random().nextInt(parcelles.size())));
                    porteGreffe.setDescription(descript[new Random().nextInt(descript.length)]);
                    greffeRepository.save(porteGreffe);
                });
    }

    @Override
    public void initParcelle() {
        //String[] observe = new String[] {};
        //Random random = new Random();
        List<PorteGreffe> porteGreffes = greffeRepository.findAll();
        secteurRepository.findAll().forEach(secteur -> {
            for (int i = 0; i < secteur.getNbrParcelle() ; i++) {
                Parcelle parcelle = new Parcelle();
                parcelle.setNumero(i+1);
                parcelle.setPorteGreffes(porteGreffes);
                parcelle.setNbrArbre((int)(Math.random()*secteur.getNbrArbre()));
                parcelle.setNbrGoutteurs((int)(Math.random()*secteur.getNbrGouteurs()));
                parcelle.setSecteur(secteur);
                parcelle.setDatePlantation(new Date());
                parcelle.setSuperficie(0.02+Math.random());
                parcelle.setObservation(RandomString.make(20));
                parcelleRepository.save(parcelle);
            }
        });
    }

    @Override
    public void initTestConf() {
        //double[] doseGoutteur = new double[] {4.00, 4.50,5.00, 5.50,6.00, 6.50,7.00, 7.50, 8.00, 8.50, 9.00, 9.50,10.00};
        //List<Double> goutteurs = new ArrayList<>(Arrays.asList(5.00, 5.50,6.00, 6.50,7.00, 7.50, 8.00, 8.50, 9.00));

        villeRepository.findAll().forEach(ville -> {
            ville.getFermes().forEach(ferme -> {
                ferme.getSecteurs().forEach(secteur -> {
                    secteur.getParcelles().forEach(parcelle -> {
                        TestConformite testConformite = new TestConformite();
                        double moymin = 1+Math.random()*2;
                        double moymax = 1+Math.random()*10;
                        double cu = (moymin/moymax);
                        if ( cu > 0.9){    testConformite.setDescription("Test bien Conforme"); }
                        else if (cu <= 0.9) {   testConformite.setDescription("Test non Conforme");}
                        testConformite.setParcelle(parcelle);
                        testConformite.setDateTest(new Date());
                        testConformite.setDebitMin(moymin);
                        testConformite.setDebitMoy(moymax);
                        testConformite.setCoefficienUniform(cu*100);
                        conformiteRepository.save(testConformite);
                    });
                });
            });
        });
    }
    @Override
    public void initApporEau() {
        double[] duree = new double[] {30,45,60,80,90,100,115,130,145,200,215,230,245};
        villeRepository.findAll().forEach(ville -> {
            ville.getFermes().forEach(ferme -> {
                ferme.getSecteurs().forEach(secteur -> {
                    pompageRepository.findByFerme(ferme).forEach(stationPompage -> {
                        ApportEau apportEau = new ApportEau();
                        apportEau.setDateOperation(new Date());
                        apportEau.setStationPompage(stationPompage);
                        apportEau.setSecteur(secteur);
                        apportEau.setET0(30+Math.random()*150);
                        apportEau.setKC(0.5+Math.random());
                        apportEau.setDureeIrrigation(duree[new Random().nextInt(duree.length)]);
                        eauRepository.save(apportEau);
                    });
                });
            });
        });
    }
}
