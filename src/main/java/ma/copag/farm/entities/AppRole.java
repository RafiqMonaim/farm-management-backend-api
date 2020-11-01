package ma.copag.farm.entities;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

}
