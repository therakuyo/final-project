package textbasedgame.finalproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "enemies")
public class EnemyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int powerLevel = 0;

    @Column(name = "gives_xp")
    private int givesXp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "enemies_items",
        joinColumns = {@JoinColumn(name = "enemy_id")},
        inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    private Set<ItemEntity> enemyItems = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "enemies_zones",
        joinColumns = {@JoinColumn(name = "enemy_id")},
        inverseJoinColumns = {@JoinColumn(name = "zone_id")}
    )
    private Set<ZoneEntity> enemyZones = new HashSet<>();
}
