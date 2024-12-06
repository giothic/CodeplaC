package codeplac.codeplac.DTO.ResponsesDTO.Ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankingResponse {
  private int idClassificacao;
  private float pontuacao;
  private RankingGroupDTO equipe;
  private RankingEventDTO evento;
}
