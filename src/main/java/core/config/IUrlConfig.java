package core.config;

import core.config.properties.CategoryNameBtn;

public interface IUrlConfig {
    String baseUrl();
    String loginUrl();
    String btnUrl(CategoryNameBtn section);
}
