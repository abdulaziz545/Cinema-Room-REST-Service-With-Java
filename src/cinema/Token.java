package cinema;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Token {
    private UUID token;

    Token() {
        this.token = UUID.randomUUID();
    }
    public void setToken(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}
