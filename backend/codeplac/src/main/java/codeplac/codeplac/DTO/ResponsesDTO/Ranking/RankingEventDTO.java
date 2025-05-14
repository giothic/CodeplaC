package codeplac.codeplac.DTO.ResponsesDTO.Ranking;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankingEventDTO {
  private int idEvento;
  private String nome;
  private LocalDate dataEvento;
}
