package org.example.di.modules.modules;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.example.utils.reporter.IReportLifecycle;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.IReportTree;
import org.example.utils.reporter.IReporter;
import org.example.utils.reporter.extentreport.ExtentTestReporter;

public class ReportModule extends AbstractModule {


    public ReportModule(){

    }

    @Override
    protected void configure() {


        bind(ExtentTestReporter.class).in(Singleton.class);
        bind(IReporter.class).to(ExtentTestReporter.class);
        bind(IReportTree.class).to(ExtentTestReporter.class);
        bind(IReportNode.class).to(ExtentTestReporter.class);
        bind(IReportLifecycle.class).to(ExtentTestReporter.class);


    }




}
