package codeplac.codeplac.DTO.RequestsDTO.Ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketRequest {
  private String qrCode;
  private Byte validacao;
  private Integer evento;
  private String usuario;
}
