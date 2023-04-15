package textbasedgame.finalproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "id")
public class AdminEntity extends UserEntity{

    @Column(name = "full_name")
    private String fullName;

    private String address;

}
