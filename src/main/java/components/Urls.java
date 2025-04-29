package components;

public enum Urls {
    PAGE1("http://www.automationpractice.pl/index.php"),
    PAGE2("https://zoo.waw.pl/"),
    PAGE3("https://www.w3schools.com/"),
    PAGE4("https://www.clickspeedtester.com/click-counter/"),
    PAGE5("https://andersenlab.com/");

    private final String url;

    Urls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
