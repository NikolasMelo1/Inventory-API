
package com.nikolas.inventory.auth;
import com.nikolas.inventory.security.JwtService;
import org.springframework.http.ResponseEntity; import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
record LoginRequest(String username, String password) {}
record TokenResponse(String token) {}
@RestController @RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager am; private final JwtService jwt;
  public AuthController(AuthenticationManager am, JwtService jwt){ this.am=am; this.jwt=jwt; }
  @PostMapping("/login") public ResponseEntity<?> login(@RequestBody LoginRequest req){
    try{ am.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
      return ResponseEntity.ok(new TokenResponse(jwt.generate(req.username())));
    }catch(AuthenticationException e){ return ResponseEntity.status(401).body("Credenciais inválidas"); } }
}