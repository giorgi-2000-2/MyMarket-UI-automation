package org.example.di;
import org.example.jsonmanager.JsonFinder;
import org.example.jsonmanager.JsonReaders;
import org.example.manager.SubcategorySearch;
import org.example.utils.config.*;
import org.example.utils.reporter.stringutils.StringSplitter;

public final class SuiteContext {
    private static final PropertiesConfig CONFIG   = new PropertiesConfig();
    private static final StringSplitter SPLITTER = new StringSplitter();
    private static final JsonFinder  FINDER   = new JsonFinder(new SubcategorySearch(), new JsonReaders());

    private SuiteContext() {}
    public static IBtnUrl btnUrl() { return CONFIG; }
    public static IWait waitConfig() { return CONFIG; }
    public static IUserConfig user() { return CONFIG; }
    public static IUrlConfig url() { return CONFIG; }
    public static StringSplitter splitter() { return SPLITTER; }
    public static JsonFinder  finder() { return FINDER; }
}