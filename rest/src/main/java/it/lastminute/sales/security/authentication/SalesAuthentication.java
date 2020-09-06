package it.lastminute.sales.security.authentication;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import it.lastminute.sales.business.jwt.JwtTokenModel;
import lombok.Getter;

@Getter
public class SalesAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 5174569304279165832L;

    private JwtTokenModel token;
    private String jwtToken;

    public SalesAuthentication(String jwtToken) {
        super(Arrays.asList());
        super.setAuthenticated(false);
        this.jwtToken = jwtToken;
    }

    public SalesAuthentication(JwtTokenModel token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);
        this.token = token;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.Authentication#getCredentials()
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.Authentication#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        return this.token;
    }

}