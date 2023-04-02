package textbasedgame.finalproject.entities;

import lombok.*;
import textbasedgame.finalproject.enums.Rarity;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String type;


    private Rarity rarity;


    @ManyToMany(mappedBy = "characterItems")
    private List<CharacterEntity> itemCharacter;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "enemies_items",
        joinColumns = {@JoinColumn(name = "item_id")},
        inverseJoinColumns = {@JoinColumn(name = "enemy_name")}
    )
    private List<EnemyEntity> itemEnemy;
}
