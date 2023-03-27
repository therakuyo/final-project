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
@Table(name = "enemies")
public class EnemyEntity {


    @Id
    private String name;

    @ManyToMany(mappedBy = "itemEnemy")
    private List<ItemEntity> enemyItems;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "enemies_zones",
        joinColumns = {@JoinColumn(name = "enemy_name")},
        inverseJoinColumns = {@JoinColumn(name = "zone_id")}
    )
    private List<ZoneEntity> enemyZones;
}
