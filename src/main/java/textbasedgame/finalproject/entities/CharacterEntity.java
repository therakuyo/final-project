package textbasedgame.finalproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "characters")
public class CharacterEntity {


    @Id
    private String name;


    private int level;


    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private ClassEntity classEntity;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "characters_items",
        joinColumns = {@JoinColumn(name = "character_name")},
        inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    private Set<ItemEntity> characterItems;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "characters_zones",
        joinColumns = {@JoinColumn(name = "character_name")},
        inverseJoinColumns = {@JoinColumn(name = "zone_id")}
    )
    private Set<ZoneEntity> characterZones;
}
