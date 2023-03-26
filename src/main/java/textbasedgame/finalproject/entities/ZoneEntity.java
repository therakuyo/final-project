package textbasedgame.finalproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "zones")
public class ZoneEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "zone_name")
    private String zoneName;


    private String difficulty;

    @ManyToMany(mappedBy = "characterZones")
    private List<CharacterEntity> characterZone;

    @ManyToMany(mappedBy = "enemyZones")
    private List<EnemyEntity> enemyZone;
}
