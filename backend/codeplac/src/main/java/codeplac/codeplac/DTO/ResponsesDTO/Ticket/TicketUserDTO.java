package codeplac.codeplac.DTO.ResponsesDTO.Ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketUserDTO {
  private String matricula;
  private String nome;
  private String sobrenome;
  private String cpf;
}
