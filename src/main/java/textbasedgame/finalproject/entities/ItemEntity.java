package textbasedgame.finalproject.entities;

import lombok.*;
import textbasedgame.finalproject.enums.ItemType;
import textbasedgame.finalproject.enums.Rarity;
import textbasedgame.finalproject.enums.Status;

import javax.persistence.*;

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

}
