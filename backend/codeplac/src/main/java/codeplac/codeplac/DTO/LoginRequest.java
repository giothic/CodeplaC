package codeplac.codeplac.DTO;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class LoginRequest {
    private String matricula;
    private String password;
}
