package components;

public enum AndersenUrls {
    REGISTRATION("https://qa-course-01.andersenlab.com/registration"),
    LOGIN("https://qa-course-01.andersenlab.com/login"),
    ACCOUNT("https://qa-course-01.andersenlab.com/"),
    SELECT("https://qa-course-01.andersenlab.com/select"),
    SEARCH_RESULTS("https://qa-course-01.andersenlab.com/search_results");


    private final String url;

    AndersenUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
