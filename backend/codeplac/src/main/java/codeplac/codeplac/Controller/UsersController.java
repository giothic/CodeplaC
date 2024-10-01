package codeplac.codeplac.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import codeplac.codeplac.Service.AuthService;
import codeplac.codeplac.DTO.LoginRequest; 
import codeplac.codeplac.DTO.LoginResponse;

import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Service.UsersService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UsersModel> cadastrarUsuario(@RequestBody UsersModel user) {
        try {
            UsersModel savedUser = usersService.createUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsersModel>> listarUsuarios() {
        List<UsersModel> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<UsersModel> obterUsuario(@PathVariable int matricula) {
        try {
            UsersModel user = usersService.getUserByMatricula(matricula);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping("/modify/{matricula}")
    public ResponseEntity<UsersModel> modificarUsuario(@PathVariable int matricula, @RequestBody UsersModel user) {
        try {
            UsersModel updatedUser = usersService.updateUser(matricula, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/destroy/{matricula}")
    public ResponseEntity<Void> apagarUsuario(@PathVariable int matricula) {
        try {
            boolean deleted = usersService.deleteUser(matricula);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.authenticate(loginRequest.getMatricula(), loginRequest.getPassword());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }
}
