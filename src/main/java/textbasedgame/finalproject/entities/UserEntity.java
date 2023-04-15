package textbasedgame.finalproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "user_name")
    private String userName;


    private String password;


    private String email;


    @OneToMany(mappedBy = "characterUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CharacterEntity> userCharacter;

}
