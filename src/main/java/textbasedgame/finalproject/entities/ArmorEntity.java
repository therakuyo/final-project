package textbasedgame.finalproject.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "armor")
@PrimaryKeyJoinColumn(name = "id")
public class ArmorEntity extends ItemEntity{

    private String name;

    @Column(name = "health_points")
    private int healthPoints;

    private int resistance;

}
