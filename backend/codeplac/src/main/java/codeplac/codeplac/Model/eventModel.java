package codeplac.codeplac.Model;

import java.time.LocalDate;
import java.util.List;

import codeplac.codeplac.Enum.eventStatus;
import codeplac.codeplac.Enum.eventTipo;
import jakarta.persistence.Entity;
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
public class eventModel {
  @Id
  private int id;

  private String nome;
  private String description;
  private LocalDate data;
  private String local;
  private eventTipo tipo;
  private eventStatus status;

  @OneToMany(mappedBy = "evento")
  private List<ticketModel> ingressos;

  @OneToMany(mappedBy = "evento")
  private List<registrationModel> inscricoes;

}