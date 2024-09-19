package codeplac.codeplac.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import codeplac.codeplac.Model.usersModel;
import codeplac.codeplac.Repository.usersRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    private final usersRepository repository;

    public CustomUserDetailService(usersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpfcnpj) throws UsernameNotFoundException {
        logger.debug("Tentando carregar usuário com CPF: {}", cpfcnpj);
        
        usersModel user = repository.findByMatricula(cpfcnpj)
            .orElseThrow(() -> {
                logger.warn("Usuário não encontrado com CPF: {}", cpfcnpj);
                return new UsernameNotFoundException("Usuário não encontrado com CPF: " + cpfcnpj);
            });

        logger.debug("Usuário encontrado: {}", user.getMatricula());
        return new org.springframework.security.core.userdetails.User(user.getMatricula(), user.getSenha(), user.getAuthorities());
    }
}