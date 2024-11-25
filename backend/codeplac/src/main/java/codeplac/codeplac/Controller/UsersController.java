package codeplac.codeplac.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import codeplac.codeplac.DTO.ResponsesDTO.User.UserResponse;
import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Service.UsersService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> cadastrarUsuario(@RequestBody UsersModel user) {
        try {
            UserResponse savedUser = usersService.createUser(user);

            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> listarUsuarios() {
        List<UserResponse> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<UserResponse> obterUsuario(@PathVariable String matricula) {
        try {
            UserResponse user = usersService.getUserByMatricula(matricula);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping("/modify/{matricula}")
    public ResponseEntity<UserResponse> modificarUsuario(
            @PathVariable String matricula,
            @RequestParam String field,
            @RequestParam String password,
            @RequestBody UsersModel user) {
        try {
            UserResponse updatedUser = usersService.updateUser(matricula, user, field, password);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("/destroy/{matricula}")
    public ResponseEntity<Void> apagarUsuario(@PathVariable String matricula) {
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
}
