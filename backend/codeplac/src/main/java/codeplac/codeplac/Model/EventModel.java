package codeplac.codeplac.Model;

import java.time.LocalDate;
import java.util.List;

import codeplac.codeplac.Enum.EventPeriodo;
import jakarta.persistence.Column;
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
  @Column(name = "id_Evento")
  private int idEvento;

  private int ano;
  private String bimestre;

  @Column(name = "dataEvento")
  private LocalDate dataEvento;

  private String lugar;

  @Enumerated(EnumType.STRING)
  private EventPeriodo periodo;

  @Column(name = "tipoEvento")
  private String tipoEvento;

  @OneToMany(mappedBy = "evento")
  private List<TicketModel> ingressos;

  @OneToMany(mappedBy = "evento")
  private List<RegistrationModel> inscricoes;

  @OneToMany(mappedBy = "evento")
  private List<RankingModel> classificacoes;
}
