package model;

public class Credential {
    private final String website;
    private final String username;
    private final String password;

    public Credential(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
