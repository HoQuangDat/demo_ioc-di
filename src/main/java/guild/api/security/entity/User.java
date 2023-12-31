package guild.api.security.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Nullable
    @Column(name = "userName", length = 30)
    @NotBlank(message = "userName isn't allow empty")
    private String userName;

    @Nullable
    @Column(name = "password", length = 255)
    @NotBlank(message = "password isn't allow empty")
    private String password;

    @Nullable
    @Column(name = "fullName", length = 30)
    @NotBlank(message = "fullName isn't allow empty")
    private String fullName;

    @Nullable
    @Column(name = "phone", length = 13)
    private String phone;

    @Nullable
    @Column(name = "address", length = 80)
    private String address;

    @Nullable
    @Column(name = "role", length = 15)
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getRole(){return role;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
