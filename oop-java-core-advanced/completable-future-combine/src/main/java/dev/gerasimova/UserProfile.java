package dev.gerasimova;
/**
 * Представляет профиль пользователя.
 * Содержит основную информацию о пользователе.
 */
class UserProfile {
    private final String userName;
    private final String email;

    public UserProfile(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Profile{userName='%s', email='%s'}", userName, email);
    }
}