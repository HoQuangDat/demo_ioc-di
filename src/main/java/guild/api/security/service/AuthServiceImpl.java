package guild.api.security.service;

import guild.api.security.entity.User;
import guild.api.security.repository.IUserRepository;
import guild.api.security.request.AuthenticationRequest;
import guild.api.security.request.RegisterRequest;
import guild.api.security.response.AuthenticationResponse;
import guild.api.security.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Object> register(RegisterRequest request) {
        //check email exists in database
        var foundUser = userRepository.findByUserName(request.getUserName());

        //check user exists in table user
        if(!foundUser.isPresent())
        {
            var user = User.builder()
                    .userName(request.getUserName())
                    .fullName(request.getFullName())
                    .address(request.getAddress())
                    .phone(request.getPhone())
                    .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

            userRepository.save(user);

            return ResponseEntity.ok().body("Register user successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Email already exists in the database");
    }

    //login user and generate token
    @Override
    public AuthenticationResponse login(AuthenticationRequest request)  {
        try {
            //send username and password from request to authenticationManager precess authentication
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()
                    )
            );

            //get information user
            var user = userRepository.findByUserName(request.getUserName()).orElseThrow();

            //generate token
            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder().token(jwtToken).build();
        } catch (AuthenticationException e) {
            // return AuthenticationResponse.builder().error("Invalid credentials").build();
            throw new RuntimeException("Authentication failed", e);
        }
    }
}
