package textbasedgame.finalproject.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "jewellery")
@PrimaryKeyJoinColumn(name = "id")
public class JewelleryEntity extends ItemEntity{


    private String name;

    @Column(name = "attack_damage")
    private int attackDamage;

    @Column(name = "magic_damage")
    private int magicDamage;

    @Column(name = "health_points")
    private int healthPoints;

    private int resistance;

}
