package core.testdata;

public enum Section {
    SELL("გაყიდვა", "sell.url"),
    BUY("შეძენა", "buy.url"),
    RENT("გაქირავება", "rent.url"),
    SERVICE("მომსახურება", "service.url");

    private final String label;
    private final String urlKey;

    Section(String label, String urlKey) {
        this.label = label;
        this.urlKey = urlKey;
    }

    public String label() {
        return label;
    }

    public String urlKey() {
        return urlKey;
    }
}
