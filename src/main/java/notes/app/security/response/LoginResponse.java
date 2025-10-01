package notes.app.security.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class LoginResponse {
    @Getter
    @Setter
    private String jwtToken;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private List<String> roles;

    public LoginResponse(String jwtToken, List<String> roles, String username) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
    }
}
