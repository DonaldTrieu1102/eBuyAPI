package net.ebuy.apiapp.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MediUser extends User {

   private static final long serialVersionUID = -3531439484732724601L;

   public MediUser(String username, String password, Collection authorities){

           super(username, password,true, true, true, true, authorities);
   }

   public static long getSerialversionuid() {
      return serialVersionUID;
   }
   
}