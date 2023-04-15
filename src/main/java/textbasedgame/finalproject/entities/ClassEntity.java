package textbasedgame.finalproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "class_name")
    private String className;


    @OneToMany(mappedBy = "characterClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CharacterEntity> classCharacters;
}
