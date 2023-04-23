package textbasedgame.finalproject.entities;

import lombok.*;
import textbasedgame.finalproject.enums.Difficulty;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "zones")
public class ZoneEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "zone_name")
    private String zoneName;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;


}
