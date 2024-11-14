package codeplac.codeplac.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    public LoginResponse(String matricula2, String token2) {
        //TODO Auto-generated constructor stub
    }
    private int matricula;
    private String token;
}
