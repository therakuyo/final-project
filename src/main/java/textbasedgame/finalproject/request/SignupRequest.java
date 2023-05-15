package textbasedgame.finalproject.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import textbasedgame.finalproject.entities.RoleEntity;
import textbasedgame.finalproject.enums.Roles;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDate birthDate;

    private int age;

    private Set<RoleEntity> roles;

}
