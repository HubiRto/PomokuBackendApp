package pl.pomoku.pomokubackendapp.request;

public record RegisterRequest(String firstName, String lastName, String email, String password) {
}
