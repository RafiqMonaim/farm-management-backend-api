package ma.copag.farm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Ferme implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 80)
    private String proprietaire,name;
    @Column(length = 100,unique = true)
    private String reference;
    @Column(length = 120)
    private String lieu;
    private double contenance,langitude,latitude;
    //calculable
    private int nbrSecteur,nbrStationPompage,nbrArbre;

    @ManyToOne
    private Ville ville;

    @OneToMany(mappedBy = "ferme")
    private Collection<Secteur> secteurs;

    @OneToMany(mappedBy = "ferme")
    private Collection<StationPompage> stationPompages;
}
