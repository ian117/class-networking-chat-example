package network_class.chat_backend.dtos;

public class UserSummaryDTO {
    private Long id;
    private String username;
    private String lastIpKnown;

    public UserSummaryDTO(Long id, String username, String lastIpKnown) {
        this.id = id;
        this.username = username;
        this.lastIpKnown = lastIpKnown;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getLastIpKnown() {
        return lastIpKnown;
    }
}