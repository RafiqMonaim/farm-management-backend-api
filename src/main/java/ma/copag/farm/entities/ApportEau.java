package ma.copag.farm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ApportEau implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOperation;
    private double ET0,KC,dureeIrrigation;
    //private int nbrStationPompage;

    @ManyToOne
    private Secteur secteur;

    @ManyToOne
    private StationPompage stationPompage;
}
