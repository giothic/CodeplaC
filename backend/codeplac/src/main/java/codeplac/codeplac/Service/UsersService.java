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

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersModel createUser(UsersModel user) throws Excecao {

        String refreshToken = UUID.randomUUID().toString();
        user.setRefreshToken(refreshToken);

        if (usersRepository.existsById(user.getMatricula())) {
            throw new Excecao("Usuário com matrícula já existe.");
        }

        user.setSenha(passwordEncoder.encode(user.getSenha()));

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

    public UsersModel updateUser(int matricula, UsersModel user) throws Excecao {
        if (usersRepository.existsById(matricula)) {
            user.setMatricula(matricula);
            return usersRepository.save(user);
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
}