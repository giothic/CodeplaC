package codeplac.codeplac.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.GroupModel;
import codeplac.codeplac.Service.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<GroupModel> criarGrupo(@RequestBody GroupModel group) {
        return new ResponseEntity<>(groupService.createGroup(group), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<GroupModel>> listarGrupos() {
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupModel> obterGrupo(@PathVariable int id) {
        try {
            return new ResponseEntity<>(groupService.getGroupById(id), HttpStatus.OK);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<GroupModel> modificarGrupo(@PathVariable int id,
            @RequestBody GroupModel group) {
        try {
            return new ResponseEntity<>(groupService.updateGroup(id, group), HttpStatus.OK);
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/destroy/{id}")
    public ResponseEntity<Void> apagarGrupo(@PathVariable int id) {
        try {
            boolean deleted = groupService.deleteGroup(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Excecao e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}