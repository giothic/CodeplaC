package codeplac.codeplac.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Model.RegistrationModel;
import codeplac.codeplac.Repository.RegistrationRepository;

@Service
public class RegistrationService {

  @Autowired
  private RegistrationRepository registrationRepository;

  public RegistrationModel createRegistration(RegistrationModel registrationModel) {
    return registrationRepository.save(registrationModel);
  }

  public List<RegistrationModel> getAllRegistrations() {
    return registrationRepository.findAll();
  }

  public RegistrationModel getRegistrationById(int id) throws Excecao {
    Optional<RegistrationModel> registration = registrationRepository.findById(id);
    if (registration.isPresent()) {
      return registration.get();
    }

    throw new Excecao("Inscrição não encontrado com id: " + id);
  }

  public RegistrationModel updateRegistration(int id, RegistrationModel registration) throws Excecao {
    if (registrationRepository.existsById(id)) {
      registration.setCodigo_grupo(registration.getCodigo_grupo());
      registration.setEvento(registration.getEvento());
      registration.setUsuario(registration.getUsuario());
      return registrationRepository.save(registration);
    } else {
      throw new Excecao("Inscrição não encontrado com id: " + id);
    }
  }

  public boolean deleteRegistration(int id) throws Excecao {
    if (registrationRepository.existsById(id)) {
      registrationRepository.deleteById(id);
    }

    throw new Excecao("Inscrição não encontrado com id: " + id);
  }
}
