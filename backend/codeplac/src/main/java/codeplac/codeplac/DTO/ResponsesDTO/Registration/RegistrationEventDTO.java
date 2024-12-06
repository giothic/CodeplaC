package codeplac.codeplac.DTO.ResponsesDTO.Registration;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationEventDTO {
  private int idEvento;
  private String nome;
  private String descricao;
  private LocalDate dataEvento;
}
