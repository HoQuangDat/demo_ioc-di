package guild.api.security.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    private String fullName;

    private String userName;

    private String password;

    private String phone;

    private String address;

    private String role;
}
