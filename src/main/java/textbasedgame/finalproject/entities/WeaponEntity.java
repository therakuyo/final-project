package textbasedgame.finalproject.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import textbasedgame.finalproject.enums.Requirements;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "weapons")
@PrimaryKeyJoinColumn(name = "id")
public class WeaponEntity extends ItemEntity{

    private String name;

    @Column(name = "attack_damage")
    private int attackDamage;

    private Requirements requirements;
}
