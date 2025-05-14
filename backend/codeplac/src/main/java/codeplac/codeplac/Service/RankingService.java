package codeplac.codeplac.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeplac.codeplac.DTO.RequestsDTO.Ranking.RankingRequest;
import codeplac.codeplac.DTO.ResponsesDTO.Ranking.RankingEventDTO;
import codeplac.codeplac.DTO.ResponsesDTO.Ranking.RankingGroupDTO;
import codeplac.codeplac.DTO.ResponsesDTO.Ranking.RankingResponse;
import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.EventModel;
import codeplac.codeplac.Model.GroupModel;
import codeplac.codeplac.Model.RankingModel;
import codeplac.codeplac.Repository.EventRepository;
import codeplac.codeplac.Repository.GroupRepository;
import codeplac.codeplac.Repository.RankingRepository;

@Service
public class RankingService {

  @Autowired
  private RankingRepository rankingRepository;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private GroupRepository groupRepository;

  public RankingResponse createRanking(RankingRequest ranking) {
    GroupModel group = groupRepository.findById(ranking.getEquipe())
        .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

    EventModel event = eventRepository.findById(ranking.getEvento())
        .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

    RankingModel newRanking = new RankingModel();

    newRanking.setPontuacao(ranking.getPontuacao());
    newRanking.setEquipe(group);
    newRanking.setEvento(event);

    rankingRepository.save(newRanking);

    return createRankingResponse(newRanking, group, event);
  }

  public List<RankingResponse> getAllRankings() {
    List<RankingModel> rankingModelsList = rankingRepository.findAll();
    List<RankingResponse> rankingResponsesList = new ArrayList<>();

    for (RankingModel rankingModel : rankingModelsList) {
      rankingResponsesList.add(createRankingResponse(rankingModel, rankingModel.getEquipe(), rankingModel.getEvento()));
    }

    return rankingResponsesList;
  }

  public RankingResponse getRankingById(int id) throws Excecao {
    Optional<RankingModel> optionalRanking = rankingRepository.findById(id);
    if (optionalRanking.isPresent()) {
      RankingModel existingRanking = optionalRanking.get();

      return createRankingResponse(existingRanking, existingRanking.getEquipe(), existingRanking.getEvento());
    }

    throw new Excecao("Classificação não encontrada com id: " + id);
  }

  public RankingResponse updateRanking(int id, RankingRequest ranking) throws Excecao {
    Optional<RankingModel> optionalRanking = rankingRepository.findById(id);

    if (optionalRanking.isPresent()) {
      RankingModel existingRanking = optionalRanking.get();

      if (ranking.getPontuacao() != 0)
        existingRanking.setPontuacao(ranking.getPontuacao());

      rankingRepository.save(existingRanking);

      return createRankingResponse(existingRanking, existingRanking.getEquipe(), existingRanking.getEvento());
    } else {
      throw new Excecao("Classificação não encontrada com id: " + id);
    }
  }

  public boolean deleteRegistration(int id) throws Excecao {
    if (rankingRepository.existsById(id)) {
      rankingRepository.deleteById(id);
      return true;
    }

    throw new Excecao("Classificação não encontrada com id: " + id);
  }

  private RankingResponse createRankingResponse(RankingModel ranking, GroupModel group, EventModel event) {
    RankingGroupDTO rankingGroupDTO = new RankingGroupDTO(
        group.getIdEquipe(),
        group.getNomeEquipe(),
        group.getNomeLider());

    RankingEventDTO rankingEventDTO = new RankingEventDTO(
        event.getIdEvento(),
        event.getNome(),
        event.getDataEvento());

    RankingResponse rankingResponse = new RankingResponse(
        ranking.getIdClassificacao(),
        ranking.getPontuacao(),
        rankingGroupDTO,
        rankingEventDTO);

    return rankingResponse;
  }
}
