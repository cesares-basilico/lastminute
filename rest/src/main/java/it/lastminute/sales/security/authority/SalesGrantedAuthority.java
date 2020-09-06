package it.lastminute.sales.security.authority;

import org.springframework.security.core.GrantedAuthority;

/**
 * Object representing the granted authority for authenticated users.
 * 
 * @author cesare
 *
 */
public class SalesGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -7494387716407821216L;

    private String role;

    /**
     * role getter.
     * 
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * role setter.
     * 
     * @param role
     *            the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Override
    public String getAuthority() {
        return this.role;
    }
}
