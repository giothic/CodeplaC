package codeplac.codeplac.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// import java.util.UUID;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Model.GroupModel;
import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Repository.GroupRepository;
import codeplac.codeplac.Repository.UsersRepository;
import codeplac.codeplac.Security.TokenService;
import codeplac.codeplac.DTO.UserRequestRegister;
import codeplac.codeplac.DTO.UserRequestUpdate;
import codeplac.codeplac.DTO.ResponsesDTO.User.UserGroupDTO;
import codeplac.codeplac.DTO.ResponsesDTO.User.UserResponse;
import codeplac.codeplac.Exception.Excecao;

@Service
public class UsersService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UserResponse createUser(UserRequestRegister user) throws Excecao {
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
        newUser.setTipoUsuario(user.getTipoUsuario());
        newUser.setMatricula(user.getMatricula());

        String refreshToken = UUID.randomUUID().toString();
        newUser.setRefreshToken(refreshToken);

        String accessToken = tokenService.generateAndStoreAccessToken(newUser);
        newUser.setAccessToken(accessToken);

        usersRepository.save(newUser);

        return createUserResponse(newUser, group);
    }

    public List<UserResponse> getAllUsers() {
        List<UsersModel> usersModelList = usersRepository.findAll();
        List<UserResponse> usersResponseList = new ArrayList<>();

        for (UsersModel userModel : usersModelList) {
            usersResponseList.add(createUserResponse(userModel, userModel.getEquipe()));
        }

        return usersResponseList;
    }

    public UserResponse getUserByMatricula(String matricula) throws Excecao {
        Optional<UsersModel> optionalUser = usersRepository.findById(matricula);
        if (optionalUser.isPresent()) {
            UserResponse userResponse = createUserResponse(optionalUser.get(), optionalUser.get().getEquipe());

            return userResponse;
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

    public UserResponse updateUser(String matricula, UserRequestUpdate user) throws Excecao {
        Optional<UsersModel> optionalUser = usersRepository.findByMatricula(matricula);

        if (optionalUser.isPresent()) {
            UsersModel existingUser = optionalUser.get();

            if (user.getEmail() != null)
                existingUser.setEmail(user.getEmail());
            if (user.getNome() != null)
                existingUser.setNome(user.getNome());
            if (user.getSobrenome() != null)
                existingUser.setSobrenome(user.getSobrenome());
            if (user.getSenha() != null)
                existingUser.setSenha(passwordEncoder.encode(user.getSenha()));
            if (user.getSobrenome() != null)
                existingUser.setSobrenome(user.getSobrenome());
            if (user.getTelefone() != null)
                existingUser.setTelefone(user.getTelefone());

            usersRepository.save(existingUser);

            UserResponse userResponse = createUserResponse(existingUser, existingUser.getEquipe());

            return userResponse;
        } else {
            throw new Excecao("Usuário não encontrado com matrícula: " + matricula);
        }
    }

    private UserResponse createUserResponse(UsersModel user, GroupModel group) {
        UserGroupDTO groupDTO = new UserGroupDTO(
                group.getIdEquipe(),
                group.getNomeEquipe(),
                group.getNomeLider());

        UserResponse userResponse = new UserResponse(
                user.getMatricula(),
                user.getEmail(),
                user.getNome(),
                user.getSobrenome(),
                user.getTelefone(),
                user.getRefreshToken(),
                user.getAccessToken(),
                groupDTO);

        return userResponse;
    }

}
