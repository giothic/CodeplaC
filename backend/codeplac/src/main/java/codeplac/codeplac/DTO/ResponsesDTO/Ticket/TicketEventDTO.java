package codeplac.codeplac.DTO.ResponsesDTO.Ticket;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketEventDTO {
  private int idEvento;
  private String nome;
  private String descricao;
  private LocalDate dataEvento;
}
