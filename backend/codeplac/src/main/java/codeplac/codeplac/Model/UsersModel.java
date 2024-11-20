package codeplac.codeplac.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import codeplac.codeplac.Enum.UserTipo;
import jakarta.persistence.Column;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel {

    @Id
    @Column(name = "matricula", length = 7)
    private String matricula;

    private String nome;
    private String sobrenome;

    @Enumerated(EnumType.STRING)
    private UserTipo tipo;

    private Integer cpf;
    private String email;
    private Integer telefone;
    private String senha;
    private String refreshToken;
    private String accessToken;

    @OneToMany(mappedBy = "usuario")
    private List<TicketModel> ingressos;

    @OneToMany(mappedBy = "usuario")
    private List<RegistrationModel> inscricoes;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "usuario_equipe", joinColumns = @JoinColumn(name = "usuario_matricula"), inverseJoinColumns = @JoinColumn(name = "equipe_id_Equipe"))
    private List<GroupModel> equipes;

}
