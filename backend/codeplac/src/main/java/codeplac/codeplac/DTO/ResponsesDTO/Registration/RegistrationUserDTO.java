package codeplac.codeplac.DTO.ResponsesDTO.Registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationUserDTO {
  private String matricula;
  private String nome;
  private String sobrenome;
  private String cpf;
}
