package codeplac.codeplac.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingresso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ticketModel {
  @Id
  private int id;

  private String qr_code;
  private int validado;

  @ManyToOne
  @JoinColumn(name = "usuario_matricula")
  private usersModel usuario;

  @ManyToOne
  @JoinColumn(name = "evento_id")
  private eventModel evento;
}
