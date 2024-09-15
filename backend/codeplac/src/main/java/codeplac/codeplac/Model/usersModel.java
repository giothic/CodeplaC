package codeplac.codeplac.Model;

import codeplac.codeplac.Enum.tipoUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class usersModel {

    @Id
    private int matricula;

    private String nome;
    private String sobrenome;
    private tipoUser tipoUser;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public tipoUser getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(tipoUser tipoUser) {
        this.tipoUser = tipoUser;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
