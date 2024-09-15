package codeplac.codeplac.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Repository.usersRepository;
import codeplac.codeplac.Model.usersModel;
import codeplac.codeplac.Exception.Excecao;

@Service
public class usersService {

    @Autowired
    private usersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public usersModel createUser(usersModel user) throws Excecao {
        if (usersRepository.existsById(user.getMatricula())) {
            throw new Excecao("Usuário com matrícula já existe.");
        }
        user.setSenha(passwordEncoder.encode(user.getSenha())); 
        return usersRepository.save(user);
    }

    public List<usersModel> getAllUsers() {
        return usersRepository.findAll();
    }

    public usersModel getUserByMatricula(int matricula) throws Excecao {
        Optional<usersModel> user = usersRepository.findById(matricula);
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

    public usersModel updateUser(int matricula, usersModel user) throws Excecao {
        if (usersRepository.existsById(matricula)) {
            user.setMatricula(matricula);
            return usersRepository.save(user);
        } else {
            throw new Excecao("Usuário não encontrado com matrícula: " + matricula);
        }
    }
}