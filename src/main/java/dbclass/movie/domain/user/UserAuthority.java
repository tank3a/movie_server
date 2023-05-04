package dbclass.movie.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AUTHORITY_USER")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@IdClass(UserAuthorityId.class)
@Builder
public class UserAuthority {

    @Id
    @Column(name = "login_id")
    private String loginId;

    @Id
    @Column(name = "authority")
    private String authority;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ADMIN_ID")
    private Admin admin;

    public void setPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
}
