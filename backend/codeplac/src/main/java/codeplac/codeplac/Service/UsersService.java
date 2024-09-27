package codeplac.codeplac.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Repository.UsersRepository;
import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Security.TokenService;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService; // Adiciona a injeção do TokenService

    public UsersModel createUser(UsersModel user) throws Excecao {
        String refreshToken = UUID.randomUUID().toString();
        user.setRefreshToken(refreshToken);

        if (usersRepository.existsById(user.getMatricula())) {
            throw new Excecao("Usuário com matrícula já existe.");
        }

        user.setSenha(passwordEncoder.encode(user.getSenha()));
        // Gera e armazena o token de acesso
        String accessToken = tokenService.generateAndStoreAccessToken(user);
        // Armazene o accessToken no usuário, se necessário
        user.setAccessToken(accessToken); // Adicione este campo ao seu modelo, se necessário

        return usersRepository.save(user);
    }

    public List<UsersModel> getAllUsers() {
        return usersRepository.findAll();
    }

    public UsersModel getUserByMatricula(int matricula) throws Excecao {
        Optional<UsersModel> user = usersRepository.findById(matricula);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Excecao("Usuário não encontrado com matrícula: " + matricula);
        }
    }


    public boolean deleteUser(int matricula) throws Excecao {
        if (usersRepository.existsById(matricula)) {
            usersRepository.deleteById(matricula);
            return true;
        } else {
            throw new Excecao("Usuário não encontrado com matrícula: " + matricula);
        }
    }

    public UsersModel updateUser(int matricula, UsersModel user) throws Excecao {
        if (usersRepository.existsById(matricula)) {
            user.setMatricula(matricula);
            return usersRepository.save(user);
        } else {
            throw new Excecao("Usuário não encontrado com matrícula: " + matricula);
        }
    }

}

