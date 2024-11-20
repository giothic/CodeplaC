package codeplac.codeplac.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
  private String matricula;
  private String email;
  private String nome;
  private Integer telefone;
  private GroupDTO equipe;
}
