package textbasedgame.finalproject.entities;

import lombok.*;
import textbasedgame.finalproject.enums.Rarity;

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

    private String type;

    @Enumerated(EnumType.STRING)
    private Rarity rarity;


}
