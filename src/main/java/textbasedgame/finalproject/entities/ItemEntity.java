package textbasedgame.finalproject.entities;

import lombok.*;
import textbasedgame.finalproject.enums.Rarity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String type;


    private Rarity rarity;


    @ManyToMany(mappedBy = "characterItems")
    private Set<CharacterEntity> itemCharacter;

    @ManyToMany(mappedBy = "enemyItems")
    private Set<EnemyEntity> itemEnemy;
}
