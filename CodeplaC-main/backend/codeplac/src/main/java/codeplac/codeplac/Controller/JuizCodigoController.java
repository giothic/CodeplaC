package codeplac.codeplac.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codeplac.codeplac.DTO.ResponsesDTO.JuizCodigoDTO;
import codeplac.codeplac.Model.JuizCodigoModel;
import codeplac.codeplac.Repository.JuizCodigoRepository;

@RestController
@RequestMapping("/juiz")
public class JuizCodigoController {
@Autowired
    private JuizCodigoRepository repository;

    @PostMapping("/enviar")
    public JuizCodigoModel enviarCodigo(@RequestBody JuizCodigoDTO dto) {
        JuizCodigoModel juiz = new JuizCodigoModel();
        juiz.setNomeEquipe(dto.getNomeEquipe());
        juiz.setNumeroCodigo(dto.getNumeroCodigo());
        juiz.setNomeLider(dto.getNomeLider());
        juiz.setCodigo(dto.getCodigo());

        return repository.save(juiz);
    }

    @GetMapping("/listar")
    public List<JuizCodigoModel> listarTodos() {
        return repository.findAll();
    }
}

