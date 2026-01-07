package com.cliente.cinelogia.config;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.authentication.AuthenticationProvider; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.AuthenticationException; 
import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.stereotype.Component;
import com.cliente.cinelogia.model.User;
import com.cliente.cinelogia.model.Authority;
import com.cliente.cinelogia.service.IUsuarioService;
import java.util.ArrayList; 
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;



@Component 
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired 
    private IUsuarioService usuarioService;

    public CustomAuthenticationProvider() 
    { 
        super(); 
    } 
    
    @Override 
        public Authentication authenticate(final Authentication authentication) 
        throws AuthenticationException 
        { 
        final String usuario = authentication.getName(); 
        String password = authentication.getCredentials().toString();

        User usuarioLogueado = usuarioService.login(usuario, password);
        if (usuarioLogueado != null) 
            { 
                final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>(); 
                for (Authority rol : usuarioLogueado.getRoles()) 
                    { 
                        grantedAuths.add(new SimpleGrantedAuthority(rol.getAuthority())); 
                    } 
                    final UserDetails principal = org.springframework.security.core.userdetails.User
                            .withUsername(usuario)
                            .password(password)
                            .authorities(grantedAuths)
                            .build();

                    final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths); 
                return auth; 
            } 
            return null; 
        } 
    @SuppressWarnings("rawtypes") 
    @Override   
    public boolean supports(final Class authentication) 
        { 
            return authentication.equals(UsernamePasswordAuthenticationToken.class); 
        } 
}
