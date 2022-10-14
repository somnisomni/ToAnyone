package anyone.to.soma.user.domain;

import anyone.to.soma.user.LoginType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Nullable
    private String uniqueId;

    private int receiveCount = 0;

    private boolean registrationFormFilled;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public void receiveLetter() {
        receiveCount++;
    }

    public User(String email, String name, LoginType loginType, String uniqueId) {
        this(null, email, name, loginType, uniqueId);
    }

    public User(String email, String name) {
        this(null, email, name, LoginType.COMMON, null);
    }

    private User(Long id, String email, String name, LoginType loginType, String uniqueId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.loginType = loginType;
        this.uniqueId = uniqueId;
        this.registrationFormFilled = false;
    }

    public void fillRegistrationForm() {
        this.registrationFormFilled = true;
    }

    public void updateProfile(Profile profile) {
        this.profile = profile;
    }
}