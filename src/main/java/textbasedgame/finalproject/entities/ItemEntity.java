package textbasedgame.finalproject.entities;

import lombok.*;
import textbasedgame.finalproject.enums.ItemType;
import textbasedgame.finalproject.enums.Rarity;
import textbasedgame.finalproject.enums.Status;

import javax.persistence.*;
import java.util.HashSet;
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

    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Enumerated(EnumType.STRING)
    private Rarity rarity;

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNEQUIPPED;

    private int price = 0;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "items_shops",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "shop_id")
    )
    private Set<ShopEntity> shopItems = new HashSet<>();

}
