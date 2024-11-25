package codeplac.codeplac.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import codeplac.codeplac.DTO.RequestsDTO.Auth.LoginRequest;
import codeplac.codeplac.DTO.ResponsesDTO.Auth.LoginResponse;
import codeplac.codeplac.Exception.Excecao;
import codeplac.codeplac.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    try {
      String token = authService.authenticate(loginRequest.getMatricula(), loginRequest.getPassword());

      return ResponseEntity.ok(new LoginResponse(loginRequest.getMatricula(), token));
    } catch (Excecao e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
    }
  }
}
