package codeplac.codeplac.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import codeplac.codeplac.Utils.JsonListConverter;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipe")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupModel {
  @Id
  @Column(name = "id_equipe")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idEquipe;

  @CreationTimestamp
  @Column(name = "data_inscricao")
  private LocalDateTime dataInscricao;

    @ElementCollection
    @CollectionTable(name = "grupo_membros", joinColumns = @JoinColumn(name = "id_equipe"))
    @Column(name = "matricula")
    private List<String> membros = new ArrayList<>();

  @Column(name = "nome_equipe")
  private String nomeEquipe;

  @Column(name = "nome_lider")
  private String nomeLider;

  @OneToMany(mappedBy = "equipe")
  private List<RankingModel> classificacoes;

  @JsonIgnore
  @ManyToMany(mappedBy = "equipes")
  private List<UsersModel> usuarios;
}