package codeplac.codeplac.DTO.ResponsesDTO.Registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationResponse {
  private int idInscricao;
  private String codigoGrupo;
  private RegistrationEventDTO evento;
  private RegistrationUserDTO usuario;
}
