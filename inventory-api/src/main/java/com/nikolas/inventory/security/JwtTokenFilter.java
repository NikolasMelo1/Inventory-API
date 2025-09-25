
package com.nikolas.inventory.security;
import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component; import java.io.IOException;
@Component public class JwtTokenFilter extends GenericFilter {
  private final JwtService jwt; private final UserDetailsService uds;
  public JwtTokenFilter(JwtService jwt, UserDetailsService uds){ this.jwt=jwt; this.uds=uds; }
  @Override public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest r=(HttpServletRequest)req; String auth=r.getHeader("Authorization");
    if(auth!=null && auth.startsWith("Bearer ")){ try{ String u=jwt.getUsername(auth.substring(7));
      UserDetails ud=uds.loadUserByUsername(u); var at=new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(at);}catch(Exception e){} }
    chain.doFilter(req,res);
  }
}