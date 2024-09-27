package codeplac.codeplac.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "grupocompetidor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String grupo_id;

  @ManyToOne
  @JoinColumn(name = "usuario_matricula")
  private UsersModel usuario;
}