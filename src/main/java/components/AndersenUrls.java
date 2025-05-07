package components;

public enum AndersenUrls {
    REGISTRATION("https://qa-course-01.andersenlab.com/registration"),
    LOGIN("https://qa-course-01.andersenlab.com/login"),
    ACCOUNT("https://qa-course-01.andersenlab.com/");

    private final String url;

    AndersenUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
