package codeplac.codeplac.Service;

import java.util.List;
import java.util.Optional;
// import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Model.GroupModel;
import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Repository.GroupRepository;
import codeplac.codeplac.Repository.UsersRepository;
import codeplac.codeplac.DTO.GroupDTO;
import codeplac.codeplac.DTO.UserCreate;
import codeplac.codeplac.DTO.UserResponse;
import codeplac.codeplac.Exception.Excecao;

@Service
public class UsersService {

    @Autowired
    private GroupRepository groupRepository;

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(UserCreate user) throws Excecao {
        if (usersRepository.existsById(user.getMatricula())) {
            throw new Excecao("Usuário com matrícula já existe.");
        }

        GroupModel group = groupRepository.findById(user.getEquipeId())
                .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));

        UsersModel newUser = new UsersModel();
        newUser.setCpf(user.getCpf());
        newUser.setEmail(user.getEmail());
        newUser.setEquipe(group);
        newUser.setNome(user.getNome());
        newUser.setSobrenome(user.getSobrenome());
        newUser.setTelefone(user.getTelefone());
        newUser.setSenha(passwordEncoder.encode(user.getSenha()));
        newUser.setTipo(user.getTipo());
        newUser.setMatricula(user.getMatricula());

        usersRepository.save(newUser);

        return createUserResponse(newUser, group);
    }

    public List<UsersModel> getAllUsers() {
        return usersRepository.findAll();
    }

    public UsersModel getUserByMatricula(String matricula) throws Excecao {
        Optional<UsersModel> user = usersRepository.findById(matricula);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Excecao("Usuário não encontrado com matrícula: " + matricula);
        }
    }

    public boolean deleteUser(String matricula) throws Excecao {
        if (usersRepository.existsById(matricula)) {
            usersRepository.deleteById(matricula);
            return true;
        } else {
            throw new Excecao("Usuário não encontrado com matrícula: " + matricula);
        }
    }

    public UsersModel updateUser(String matricula, UsersModel user) throws Excecao {
        Optional<UsersModel> optionalUser = usersRepository.findByMatricula(matricula);

        if (optionalUser.isPresent()) {
            UsersModel existingUser = optionalUser.get();

            if (user.getCpf() != null)
                existingUser.setCpf(user.getCpf());
            if (user.getEmail() != null)
                existingUser.setEmail(user.getEmail());
            if (user.getNome() != null)
                existingUser.setNome(user.getNome());
            if (user.getSenha() != null)
                existingUser.setSenha(passwordEncoder.encode(user.getSenha()));
            if (user.getSobrenome() != null)
                existingUser.setSobrenome(user.getSobrenome());
            if (user.getTelefone() != null)
                existingUser.setTelefone(user.getTelefone());
            if (user.getTipo() != null)
                existingUser.setTipo(user.getTipo());

            return usersRepository.save(existingUser);
        } else {
            throw new Excecao("Usuário não encontrado com matrícula: " + matricula);
        }
    }

    private UserResponse createUserResponse(UsersModel user, GroupModel group) {
        GroupDTO groupDTO = new GroupDTO(group.getIdEquipe(), group.getNomeEquipe(), group.getNomeLider());

        UserResponse userResponse = new UserResponse(user.getMatricula(), user.getEmail(), user.getNome(),
                user.getTelefone(), groupDTO);

        return userResponse;
    }

}
