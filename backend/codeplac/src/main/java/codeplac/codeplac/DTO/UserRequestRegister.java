package codeplac.codeplac.DTO;

import codeplac.codeplac.Enum.UserTipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestRegister {
  private String matricula;
  private String cpf;
  private String email;
  private String nome;
  private String senha;
  private String sobrenome;
  private String telefone;
  private UserTipo tipoUsuario;
  private int equipeId;
}