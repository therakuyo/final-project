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
@Table(name = "items")
public class ItemEntity {


    @Id
    private String type;


    @Column(name = "attack_damage")
    private int attackDamage;


    @Column(name = "magic_damage")
    private int magicDamage;


    private int armor;


    @Column(name = "health_points")
    private int healthPoints;


    @ManyToMany(mappedBy = "characterItems")
    private List<CharacterEntity> characterItem;

    @ManyToMany(mappedBy = "enemyItems")
    private List<EnemyEntity> enemyItem;
}
