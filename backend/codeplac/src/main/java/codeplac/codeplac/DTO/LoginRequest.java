package codeplac.codeplac.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private int matricula;
    private String password;
}
