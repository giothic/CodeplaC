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

import codeplac.codeplac.DTO.RequestsDTO.Ranking.RankingRequest;
import codeplac.codeplac.DTO.ResponsesDTO.Ranking.RankingResponse;
import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Service.RankingService;

@RestController
@RequestMapping("/ranking")
public class RankingController {

  @Autowired
  private RankingService rankingService;

  @PostMapping("/create")
  public ResponseEntity<RankingResponse> criarRanking(@RequestBody RankingRequest ranking) {
    return new ResponseEntity<>(rankingService.createRanking(ranking), HttpStatus.CREATED);
  }

  @GetMapping("/list")
  public ResponseEntity<List<RankingResponse>> listarRankings() {
    return new ResponseEntity<>(rankingService.getAllRankings(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RankingResponse> obterRanking(@PathVariable int id) {
    try {
      return new ResponseEntity<>(rankingService.getRankingById(id), HttpStatus.OK);
    } catch (Excecao e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PutMapping("/modify/{id}")
  public ResponseEntity<RankingResponse> modificarRanking(@PathVariable int id, @RequestBody RankingRequest ranking) {
    try {
      return new ResponseEntity<>(rankingService.updateRanking(id, ranking), HttpStatus.OK);
    } catch (Excecao e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @DeleteMapping("/destroy/{id}")
  public ResponseEntity<Void> apagarRanking(@PathVariable int id) {
    try {
      boolean deleted = rankingService.deleteRegistration(id);
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
