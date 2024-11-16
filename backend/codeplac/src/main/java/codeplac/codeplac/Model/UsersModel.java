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
import jakarta.persistence.ManyToOne;
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
    @Column(name = "matricula", length = 8)
    private String matricula;

    private String cpf;
    private String email;
    private String nome;
    private String senha;
    private String sobrenome;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private UserTipo tipo;

    @OneToMany(mappedBy = "usuario")
    private List<TicketModel> ingressos;

    @OneToMany(mappedBy = "usuario")
    private List<RegistrationModel> inscricoes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "equipe_id_Equipe")
    private GroupModel equipe;
}
