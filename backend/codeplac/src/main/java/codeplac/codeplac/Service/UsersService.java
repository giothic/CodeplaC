package codeplac.codeplac.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// import java.util.UUID;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Repository.UsersRepository;
import codeplac.codeplac.Security.TokenService;
import codeplac.codeplac.DTO.ResponsesDTO.User.UserResponse;
import codeplac.codeplac.Exception.Excecao;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UserResponse createUser(UsersModel user) throws Excecao {
        if (usersRepository.existsById(user.getMatricula())) {
            throw new Excecao("Usuário com matrícula já existe.");
        }

        UsersModel newUser = new UsersModel();
        newUser.setCpf(user.getCpf());
        newUser.setEmail(user.getEmail());
        newUser.setEquipes(new ArrayList<>());
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

        return createUserResponse(newUser);
    }

    public List<UserResponse> getAllUsers() {
        List<UsersModel> usersModelList = usersRepository.findAll();
        List<UserResponse> usersResponseList = new ArrayList<>();

        for (UsersModel userModel : usersModelList) {
            usersResponseList.add(createUserResponse(userModel));
        }

        return usersResponseList;
    }

    public UserResponse getUserByMatricula(String matricula) throws Excecao {
        Optional<UsersModel> optionalUser = usersRepository.findById(matricula);
        if (optionalUser.isPresent()) {
            UserResponse userResponse = createUserResponse(optionalUser.get());

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

    public UserResponse updateUser(String matricula, UserRequestUpdate user, String field, String password)
            throws Excecao {
        Optional<UsersModel> optionalUser = usersRepository.findByMatricula(matricula);

        if (optionalUser.isPresent()) {
            UsersModel existingUser = optionalUser.get();

            if (!passwordEncoder.matches(password, existingUser.getSenha())) {
                throw new Excecao("Senha incorreta.");
            }

            switch (field) {
                case "email":
                    if (user.getEmail() != null)
                        existingUser.setEmail(user.getEmail());
                    break;
                case "nome":
                    if (user.getNome() != null)
                        existingUser.setNome(user.getNome());
                    break;
                case "sobrenome":
                    if (user.getSobrenome() != null)
                        existingUser.setSobrenome(user.getSobrenome());
                    break;
                case "telefone":
                    if (user.getTelefone() != null)
                        existingUser.setTelefone(user.getTelefone());
                    break;
                case "senha":
                    if (user.getSenha() != null)
                        existingUser.setSenha(passwordEncoder.encode(user.getSenha()));
                    break;
                default:
                    throw new Excecao("Campo inválido: " + field);
            }

            usersRepository.save(existingUser);

            return createUserResponse(existingUser);
        } else {
            throw new Excecao("Usuário não encontrado com matrícula: " + matricula);
        }
    }

    private UserResponse createUserResponse(UsersModel user) {

        UserResponse userResponse = new UserResponse(
                user.getMatricula(),
                user.getEmail(),
                user.getNome(),
                user.getSobrenome(),
                user.getTelefone(),
                user.getCpf(),
                user.getTipoUsuario(),
                user.getRefreshToken(),
                user.getAccessToken());

        return userResponse;
    }

}
