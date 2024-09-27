package codeplac.codeplac.Model;

import java.time.LocalDate;
import java.util.List;

import codeplac.codeplac.Enum.EventStatus;
import codeplac.codeplac.Enum.EventTipo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {
  @Id
  private int id;

  private String nome;
  private String description;
  private LocalDate data;
  private String local;

  @Enumerated(EnumType.STRING)
  private EventTipo tipo;
  @Enumerated(EnumType.STRING)
  private EventStatus status;

  @OneToMany(mappedBy = "evento")
  private List<TicketModel> ingressos;

  @OneToMany(mappedBy = "evento")
  private List<RegistrationModel> inscricoes;

}
