package xyz.imaf6971.volgaitfinal.model;

public enum Roles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MODERATOR("ROLE_MODERATOR");
    public final String value;

    Roles(String value) {
        this.value = value;
    }
}
