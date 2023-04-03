package textbasedgame.finalproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "enemies")
public class EnemyEntity {


    @Id
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "enemies_items",
        joinColumns = {@JoinColumn(name = "enemy_name")},
        inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    private Set<ItemEntity> enemyItems;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "enemies_zones",
        joinColumns = {@JoinColumn(name = "enemy_name")},
        inverseJoinColumns = {@JoinColumn(name = "zone_id")}
    )
    private Set<ZoneEntity> enemyZones;
}
