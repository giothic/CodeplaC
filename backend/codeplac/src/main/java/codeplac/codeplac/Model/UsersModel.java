package codeplac.codeplac.Model;

import java.util.List;

import codeplac.codeplac.Enum.UserTipo;
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
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel {

    @Id
    private int matricula;

    private String nome;
    private String sobrenome;

    @Enumerated(EnumType.STRING)
    private UserTipo tipoUser;

    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private String refreshToken;
    private String accessToken;

    @OneToMany(mappedBy = "usuario")
    private List<GroupModel> grupos;

    @OneToMany(mappedBy = "usuario")
    private List<TicketModel> ingressos;

    @OneToMany(mappedBy = "usuario")
    private List<RegistrationModel> inscricoes;

}
