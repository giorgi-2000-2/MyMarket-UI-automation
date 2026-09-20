package core.config.properties;

public enum Section {
    SELL("გაყიდვა", "sell.url"),
    BUY("ყიდვა", "buy.url"),
    RENT("გაქირავება", "rent.url"),
    SERVICE("მომსახურება", "service.url");

    private final String name;
    private final String urlKey;

    Section(String name, String urlKey) {
        this.name = name;
        this.urlKey = urlKey;
    }

    public String getName() {
        return name;
    }

    public String getUrlKey() {
        return urlKey;
    }
}