package textbasedgame.finalproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "player")
@PrimaryKeyJoinColumn(name = "id")
public class PlayerEntity extends UserEntity{

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "full_name")
    private String fullName;

}
