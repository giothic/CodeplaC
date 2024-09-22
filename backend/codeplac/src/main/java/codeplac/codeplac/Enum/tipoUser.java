package codeplac.codeplac.Enum;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum tipoUser {

    ADMIN(Set.of(new SimpleGrantedAuthority("ROLE_ADMIN"))),
    PARTICIPANT(Set.of(new SimpleGrantedAuthority("ROLE_PARTICIPANT")));

    private final Set<GrantedAuthority> authorities;

    tipoUser(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}