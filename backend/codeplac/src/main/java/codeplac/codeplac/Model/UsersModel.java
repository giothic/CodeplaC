package codeplac.codeplac.Model;

import java.util.List;

import codeplac.codeplac.Enum.UserTipo;
import jakarta.persistence.Column;
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

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "tipo_usuario")
    @Enumerated(EnumType.STRING)
    private UserTipo tipoUsuario;

    private String cpf;
    private String email;
    private String telefone;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<TicketModel> ingressos;

    @OneToMany(mappedBy = "usuario")
    private List<RegistrationModel> inscricoes;

    @ManyToMany
    @JoinTable(name = "usuario_equipe", joinColumns = @JoinColumn(name = "usuario_matricula"), inverseJoinColumns = @JoinColumn(name = "equipe_id_Equipe"))
    private List<GroupModel> equipes;
}
