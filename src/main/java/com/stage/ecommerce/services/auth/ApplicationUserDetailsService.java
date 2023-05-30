package com.stage.ecommerce.services.auth;

import com.stage.ecommerce.dto.UtilisateurDto;
import com.stage.ecommerce.model.auth.ExtendedUser;
import com.stage.ecommerce.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private IUtilisateurService utilisateurService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        UtilisateurDto utilisateur = utilisateurService.findByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
       try{
           utilisateur.getUtilisateurRole().forEach(role -> authorities.add(
                   new SimpleGrantedAuthority(role.getRole().getName())
           ));
       }catch (Exception e){
           e.getMessage();
       }

       System.out.println("User email == "+ utilisateur.getEmail());
       System.out.println("User password == "+ utilisateur.getMotsdepasse());
       System.out.println("User role == "+ authorities);

//         return new User(utilisateur.getEmail(), utilisateur.getMotsdepasse(), Collections.emptyList());
        return new ExtendedUser(utilisateur.getEmail(), utilisateur.getMotsdepasse(), authorities);
    }
}
