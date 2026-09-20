package mobile.category.parsing;

import com.google.inject.Inject;
import core.reporter.ReportMessages;
import io.appium.java_client.AppiumDriver;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;


public class PageSourceParser {
    private final AppiumDriver driver;
    private final DocumentBuilder xml;

    @Inject
    public PageSourceParser(AppiumDriver driver) {
        this.driver = driver;
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setNamespaceAware(false);
            this.xml = f.newDocumentBuilder();
        } catch (Exception e) {
            throw new IllegalStateException(ReportMessages.XML_PARSER_CREATION_FAILED.format(e));
        }
    }

    public Document parse() {
        try {
            byte[] src = driver.getPageSource().getBytes(StandardCharsets.UTF_8);
            return xml.parse(new ByteArrayInputStream(src));
        } catch (Exception e) {
            throw new IllegalStateException(ReportMessages.PAGE_SOURCE_PARSE_FAILED.format(e));
        }
    }
}
