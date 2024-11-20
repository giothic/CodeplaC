package codeplac.codeplac.Enum;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserTipo {

    ADMIN(Set.of(new SimpleGrantedAuthority("ROLE_ADMIN"))),
    Participante(Set.of(new SimpleGrantedAuthority("ROLE_Participante"))),
    Membro(Set.of(new SimpleGrantedAuthority("ROLE_Participante"))),
    Lider(Set.of(new SimpleGrantedAuthority("ROLE_Participante")));

    private final Set<GrantedAuthority> authorities;

    UserTipo(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
