package codeplac.codeplac.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.usersModel;
import codeplac.codeplac.Service.usersService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class usersController {

    @Autowired
    private usersService usersService;

    @PostMapping("/cadastrar")
    public ResponseEntity<usersModel> cadastrarUsuario(@RequestBody usersModel user) {
        try {
            usersModel savedUser = usersService.createUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<usersModel>> listarUsuarios() {
        List<usersModel> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<usersModel> obterUsuario(@PathVariable int matricula) {
        try {
            usersModel user = usersService.getUserByMatricula(matricula);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping("/modificar/{matricula}")
    public ResponseEntity<usersModel> modificarUsuario(@PathVariable int matricula, @RequestBody usersModel user) {
        try {
            usersModel updatedUser = usersService.updateUser(matricula, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/apagar/{matricula}")
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
}