package guild.api.security.controller.interfaces;

import guild.api.security.request.AuthenticationRequest;
import guild.api.security.request.RegisterRequest;
import guild.api.security.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    public ResponseEntity<Object> register(RegisterRequest request);
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request);
}