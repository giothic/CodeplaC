package codeplac.codeplac.DTO.ResponsesDTO.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserGroupDTO {
  private int idEquipe;
  private String nomeEquipe;
  private String nomeLider;
}
