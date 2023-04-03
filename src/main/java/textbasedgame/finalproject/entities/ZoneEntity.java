package textbasedgame.finalproject.entities;

import lombok.*;
import textbasedgame.finalproject.enums.Difficulty;

import javax.persistence.*;
import java.util.Set;

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


    private Difficulty difficulty;

    @ManyToMany(mappedBy = "characterZones")
    private Set<CharacterEntity> zoneCharacter;

    @ManyToMany(mappedBy = "enemyZones")
    private Set<EnemyEntity> zoneEnemy;
}
