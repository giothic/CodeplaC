package codeplac.codeplac.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeplac.codeplac.DTO.RequestsDTO.Registration.RegistrationRequest;
import codeplac.codeplac.DTO.ResponsesDTO.Registration.RegistrationEventDTO;
import codeplac.codeplac.DTO.ResponsesDTO.Registration.RegistrationResponse;
import codeplac.codeplac.DTO.ResponsesDTO.Registration.RegistrationUserDTO;
import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.EventModel;
import codeplac.codeplac.Model.RegistrationModel;
import codeplac.codeplac.Model.UsersModel;
import codeplac.codeplac.Repository.EventRepository;
import codeplac.codeplac.Repository.RegistrationRepository;
import codeplac.codeplac.Repository.UsersRepository;

@Service
public class RegistrationService {

  @Autowired
  private RegistrationRepository registrationRepository;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private UsersRepository usersRepository;

  public RegistrationResponse createRegistration(RegistrationRequest registration) {
    EventModel event = eventRepository.findById(registration.getEvento())
        .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

    UsersModel user = usersRepository.findById(registration.getUsuario())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    RegistrationModel newRegistration = new RegistrationModel();

    newRegistration.setCodigoGrupo(registration.getCodigoGrupo());
    newRegistration.setEvento(event);
    newRegistration.setUsuario(user);

    registrationRepository.save(newRegistration);

    return createRegistrationResponse(newRegistration, event, user);
  }

  public List<RegistrationResponse> getAllRegistrations() {
    List<RegistrationModel> registrationModelsList = registrationRepository.findAll();
    List<RegistrationResponse> registrationResponsesList = new ArrayList<>();

    for (RegistrationModel registrationModel : registrationModelsList) {
      registrationResponsesList.add(
          createRegistrationResponse(registrationModel, registrationModel.getEvento(), registrationModel.getUsuario()));
    }

    return registrationResponsesList;
  }

  public RegistrationResponse getRegistrationById(int id) throws Excecao {
    Optional<RegistrationModel> optionalRegistration = registrationRepository.findById(id);
    if (optionalRegistration.isPresent()) {
      RegistrationModel existingRegistration = optionalRegistration.get();

      return createRegistrationResponse(existingRegistration, existingRegistration.getEvento(),
          existingRegistration.getUsuario());
    }

    throw new Excecao("Inscrição não encontrado com id: " + id);
  }

  public RegistrationResponse updateRegistration(int id, RegistrationRequest registration) throws Excecao {
    Optional<RegistrationModel> optionalRegistration = registrationRepository.findById(id);

    if (optionalRegistration.isPresent()) {
      RegistrationModel existingRegistration = optionalRegistration.get();

      if (registration.getCodigoGrupo() != null)
        existingRegistration.setCodigoGrupo(registration.getCodigoGrupo());

      registrationRepository.save(existingRegistration);

      return createRegistrationResponse(
          existingRegistration,
          existingRegistration.getEvento(),
          existingRegistration.getUsuario());
    } else {
      throw new Excecao("Inscrição não encontrado com id: " + id);
    }
  }

  public boolean deleteRegistration(int id) throws Excecao {
    if (registrationRepository.existsById(id)) {
      registrationRepository.deleteById(id);
      return true;
    }

    throw new Excecao("Inscrição não encontrado com id: " + id);
  }

  private RegistrationResponse createRegistrationResponse(
      RegistrationModel registration, EventModel event, UsersModel user) {
    RegistrationEventDTO registrationEventDTO = new RegistrationEventDTO(event.getIdEvento(),
        event.getNome(),
        event.getDescricao(),
        event.getDataEvento());

    RegistrationUserDTO registrationUserDTO = new RegistrationUserDTO(
        user.getMatricula(),
        user.getNome(),
        user.getSobrenome(),
        user.getCpf());

    RegistrationResponse registrationResponse = new RegistrationResponse(
        registration.getIdInscricao(),
        registration.getCodigoGrupo(),
        registrationEventDTO,
        registrationUserDTO);

    return registrationResponse;
  }
}
