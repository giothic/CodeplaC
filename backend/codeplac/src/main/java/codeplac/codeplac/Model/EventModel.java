package codeplac.codeplac.Model;

import java.time.LocalDate;
import java.util.List;

import codeplac.codeplac.Enum.EventPeriodo;
import codeplac.codeplac.Enum.EventTipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
  @Column(name = "id_evento")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idEvento;

  private Integer ano;
  private String bimestre;

  @Column(name = "data_evento")
  private LocalDate dataEvento;

  private String nome;
  private String descricao;
  private String lugar;

  @Enumerated(EnumType.STRING)
  private EventPeriodo periodo;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_evento")
  private EventTipo tipoEvento;

  @OneToMany(mappedBy = "evento")
  private List<TicketModel> ingressos;

  @OneToMany(mappedBy = "evento")
  private List<RegistrationModel> inscricoes;

  @OneToMany(mappedBy = "evento")
  private List<RankingModel> classificacoes;
}
