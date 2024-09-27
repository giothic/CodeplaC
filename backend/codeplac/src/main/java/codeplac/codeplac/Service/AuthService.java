package codeplac.codeplac.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Security.TokenService;

@Service
public class AuthService {

    @Autowired
    private UsersService usersService; // Injeção de dependência

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public String authenticate(int matricula, String password) throws Excecao {
        System.out.println("Matrícula: " + matricula); // Log da matrícula
        UsersModel user = usersService.getUserByMatricula(matricula);
        
        if (user == null) {
            System.out.println("Usuário não encontrado!"); // Log se o usuário não existir
            throw new Excecao("Usuário ou senha inválidos");
        }
    
        if (!passwordEncoder.matches(password, user.getSenha())) {
            System.out.println("Senha incorreta!"); // Log se a senha não corresponder
            throw new Excecao("Usuário ou senha inválidos");
        }
    
        return tokenService.generateToken(user);
    }

}
