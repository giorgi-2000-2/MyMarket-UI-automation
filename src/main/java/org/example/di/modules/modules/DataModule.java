package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.example.TestDataPreparer;
import org.example.di.modules.TestContext;
import org.example.di.modules.TestScope;
import org.example.jsonmanager.CategoryDataService;
import org.example.jsonmanager.JsonFinder;
import org.example.jsonmanager.JsonReaders;
import org.example.jsonmanager.SubcategorySearch;
import org.example.utils.reporter.stringutils.StringSplitter;


public class DataModule extends AbstractModule {
    public final TestScope TEST_SCOPE;

    public DataModule(TestScope testScope){
        TEST_SCOPE = testScope;
    }

    @Override
    protected void configure() {
        bind(TestContext.class).in(TEST_SCOPE);

        bind(TestDataPreparer.class).in(TEST_SCOPE);
        bind(JsonReaders.class).in(Singleton.class);
        bind(SubcategorySearch.class).in(Singleton.class);
        bind(JsonFinder.class).in(Singleton.class);
        bind(StringSplitter.class).in(Singleton.class);
        bind(CategoryDataService.class).in(Singleton.class);
    }




}
