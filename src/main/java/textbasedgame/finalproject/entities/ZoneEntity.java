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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "characters_zones",
        joinColumns = {@JoinColumn(name = "zone_id")},
        inverseJoinColumns = {@JoinColumn(name = "character_name")}
    )
    private List<CharacterEntity> zoneCharacter;

    @ManyToMany(mappedBy = "enemyZones")
    private List<EnemyEntity> zoneEnemy;
}
