package codeplac.codeplac.DTO.ResponsesDTO.User;

import codeplac.codeplac.Enum.UserTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
  private String matricula;
  private String email;
  private String nome;
  private String sobrenome;
  private String telefone;
  private String cpf;
  private UserTipo tipoUsuario;
  private String refreshToken;
  private String accessToken;
}
