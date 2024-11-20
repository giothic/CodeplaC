package codeplac.codeplac.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestUpdate {
  private String email;
  private String nome;
  private String senha;
  private String sobrenome;
  private String telefone;
  private int equipeId;
}
