package ma.copag.farm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Secteur implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 80)
    private String nom;
    private String observation;
    private double langitude,latitude,superficie;
    private double DebitGoutteur,Densite,DensiteTotale,pluviometrieSys,ecartLigne,ecartInterligne;
    private int nbrParcelle,nbrArbre,nbrGouteurs;

    @ManyToOne
    private Ferme ferme;


    @OneToMany(mappedBy = "secteur")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Parcelle> parcelles;

    @OneToMany(mappedBy = "secteur")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<ApportEau> apportEaus;
}
