package codeplac.codeplac.DTO.ResponsesDTO.Ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketResponse {
  private int idIngresso;
  private String qrCode;
  private Byte validacao;
  private TicketEventDTO evento;
  private TicketUserDTO usuario;
}
