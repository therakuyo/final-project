package textbasedgame.finalproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private ClassEntity characterClass;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "characters_items",
        joinColumns = @JoinColumn(name = "character_name"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<ItemEntity> characterItems = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "characters_zones",
        joinColumns = @JoinColumn(name = "character_name"),
        inverseJoinColumns = @JoinColumn(name = "zone_id")
    )
    private Set<ZoneEntity> characterZones = new HashSet<>();



}
