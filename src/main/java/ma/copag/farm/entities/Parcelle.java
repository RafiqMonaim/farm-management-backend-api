package ma.copag.farm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Parcelle implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private double superficie,densite,ecartLigne,ecartInterligne;
    @Temporal(TemporalType.DATE)
    private Date datePlantation;
    //calculable
    private int nbrArbre,nbrGoutteurs;
    private String observation;

    @ManyToOne
    private Secteur secteur;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Variete variete;

    @OneToMany(mappedBy = "parcelle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<PorteGreffe> porteGreffes;

    @OneToMany(mappedBy = "parcelle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<TestConformite> testConformites;

}
