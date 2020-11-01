package ma.copag.farm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class StationPompage implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String nom,type;
    private double puissance,langitude,latitude;

    @ManyToOne
    private Ferme ferme;

    @OneToMany(mappedBy = "stationPompage")
    private Collection<ApportEau> apportEaus;
}
