package codeplac.codeplac.DTO.ResponsesDTO.Group;

import java.time.LocalDateTime;
import java.util.List;

import codeplac.codeplac.Model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupResponse {
  private int idEquipe;
  private String nomeEquipe;
  private String nomeLider;
  private LocalDateTime dataInscricao;
  private List<Member> membros;
}
