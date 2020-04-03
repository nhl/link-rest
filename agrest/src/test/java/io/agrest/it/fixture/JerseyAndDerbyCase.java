package io.agrest.it.fixture;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.agrest.it.fixture.cayenne.E1;
import io.agrest.it.fixture.cayenne.E10;
import io.agrest.it.fixture.cayenne.E11;
import io.agrest.it.fixture.cayenne.E12;
import io.agrest.it.fixture.cayenne.E12E13;
import io.agrest.it.fixture.cayenne.E13;
import io.agrest.it.fixture.cayenne.E14;
import io.agrest.it.fixture.cayenne.E15;
import io.agrest.it.fixture.cayenne.E15E1;
import io.agrest.it.fixture.cayenne.E17;
import io.agrest.it.fixture.cayenne.E18;
import io.agrest.it.fixture.cayenne.E19;
import io.agrest.it.fixture.cayenne.E2;
import io.agrest.it.fixture.cayenne.E20;
import io.agrest.it.fixture.cayenne.E21;
import io.agrest.it.fixture.cayenne.E22;
import io.agrest.it.fixture.cayenne.E23;
import io.agrest.it.fixture.cayenne.E24;
import io.agrest.it.fixture.cayenne.E25;
import io.agrest.it.fixture.cayenne.E26;
import io.agrest.it.fixture.cayenne.E27Nopk;
import io.agrest.it.fixture.cayenne.E3;
import io.agrest.it.fixture.cayenne.E4;
import io.agrest.it.fixture.cayenne.E5;
import io.agrest.it.fixture.cayenne.E6;
import io.agrest.it.fixture.cayenne.E7;
import io.agrest.it.fixture.cayenne.E8;
import io.agrest.it.fixture.cayenne.E9;
import io.agrest.it.fixture.cayenne.aggregate1.A11;
import io.agrest.it.fixture.cayenne.aggregate1.A12;
import io.agrest.it.fixture.cayenne.aggregate1.A13;
import io.agrest.it.fixture.cayenne.aggregate1.A14;
import io.agrest.runtime.AgBuilder;
import io.agrest.runtime.AgRuntime;
import io.agrest.runtime.IAgService;
import io.agrest.runtime.cayenne.CayennePersister;
import io.agrest.runtime.cayenne.ICayennePersister;
import io.bootique.BQRuntime;
import io.bootique.cayenne.CayenneModule;
import io.bootique.cayenne.test.CayenneTestDataManager;
import io.bootique.jdbc.test.Table;
import io.bootique.jersey.JerseyModule;
import io.bootique.jersey.JerseyModuleExtender;
import io.bootique.test.junit.BQTestFactory;
import org.apache.cayenne.configuration.server.ServerModule;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.junit.ClassRule;
import org.junit.Rule;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 * An abstract superclass of integration tests that starts Bootique test runtime with JAX-RS service and Derby DB.
 */
public abstract class JerseyAndDerbyCase {

    @ClassRule
    public static final BQTestFactory TEST_FACTORY = new BQTestFactory();
    private static final CayenneOpCounter CAYENNE_OP_COUNTER = new CayenneOpCounter();
    protected static BQRuntime TEST_RUNTIME;

    @Rule
    public CayenneTestDataManager dataManager = createDataManager(TEST_RUNTIME);

    @Rule
    public CayenneOpCounter cayenneOpCounter = CAYENNE_OP_COUNTER;

    protected static void startTestRuntime(Class<?>... resources) {
        startTestRuntime(b -> b, resources);
    }

    protected static void startTestRuntime(UnaryOperator<AgBuilder> agCustomizer, Class<?>... resources) {
        startTestRuntime((b, p) -> agCustomizer.apply(b), resources);
    }

    protected static void startTestRuntime(BiFunction<AgBuilder, ICayennePersister, AgBuilder> agCustomizer, Class<?>... resources) {

        TEST_RUNTIME = TEST_FACTORY.app("-s", "-c", "classpath:io/agrest/it/fixture/server.yml")
                .autoLoadModules()
                .module(new AgModule(agCustomizer))
                .module(b -> CayenneModule.extend(b).addModule(initCayenneOpCounter()).addProject("cayenne-agrest-tests.xml"))
                .module(b -> addResources(JerseyModule.extend(b), resources).addFeature(AgRuntime.class))
                .createRuntime();

        TEST_RUNTIME.run();
    }

    private static org.apache.cayenne.di.Module initCayenneOpCounter() {
        return b -> ServerModule.contributeDomainFilters(b).add(CAYENNE_OP_COUNTER);
    }

    private static JerseyModuleExtender addResources(JerseyModuleExtender extender, Class<?>... resources) {
        for (Class<?> c : resources) {
            extender.addResource(c);
        }

        return extender;
    }

    protected CayenneTestDataManager createDataManager(BQRuntime runtime) {
        return CayenneTestDataManager.builder(TEST_RUNTIME)
                .entities(testEntities())
                .entitiesAndDependencies(testEntitiesAndDependencies())
                .build();
    }

    protected abstract Class<?>[] testEntities();

    protected Class<?>[] testEntitiesAndDependencies() {
        return new Class[0];
    }

    protected CayenneAwareResponseAssertions onSuccess(Response response) {
        return onResponse(response).wasSuccess();
    }

    protected CayenneAwareResponseAssertions onResponse(Response response) {
        return new CayenneAwareResponseAssertions(response, cayenneOpCounter);
    }

    protected String urlEnc(String queryParam) {
        try {
            // URLEncoder replaces spaces with "+"... Those are not decoded
            // properly by Jersey in 'uriInfo.getQueryParameters()' (TODO: why?)
            return URLEncoder.encode(queryParam, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {

            // unexpected... we know that UTF-8 is present
            throw new RuntimeException(e);
        }
    }

    protected IAgService ag() {
        return agService(IAgService.class);
    }

    protected <T> T agService(Class<T> type) {
        return TEST_RUNTIME.getInstance(AgRuntime.class).service(type);
    }

    protected WebTarget target(String path) {
        return ClientBuilder.newClient().target("http://127.0.0.1:8080/").path(path);
    }

    protected Table a11() {
        return dataManager.getTable(A11.class);
    }

    protected Table a12() {
        return dataManager.getTable(A12.class);
    }

    protected Table a13() {
        return dataManager.getTable(A13.class);
    }

    protected Table a14() {
        return dataManager.getTable(A14.class);
    }

    protected Table e1() {
        return dataManager.getTable(E1.class);
    }

    protected Table e2() {
        return dataManager.getTable(E2.class);
    }

    protected Table e3() {
        return dataManager.getTable(E3.class);
    }

    protected Table e4() {
        return dataManager.getTable(E4.class);
    }

    protected Table e5() {
        return dataManager.getTable(E5.class);
    }

    protected Table e6() {
        return dataManager.getTable(E6.class);
    }

    protected Table e7() {
        return dataManager.getTable(E7.class);
    }

    protected Table e8() {
        return dataManager.getTable(E8.class);
    }

    protected Table e9() {
        return dataManager.getTable(E9.class);
    }

    protected Table e10() {
        return dataManager.getTable(E10.class);
    }

    protected Table e11() {
        return dataManager.getTable(E11.class);
    }

    protected Table e12() {
        return dataManager.getTable(E12.class);
    }

    protected Table e13() {
        return dataManager.getTable(E13.class);
    }

    protected Table e12_13() {
        return dataManager.getTable(E12E13.class);
    }

    protected Table e14() {
        return dataManager.getTable(E14.class);
    }

    protected Table e15() {
        return dataManager.getTable(E15.class);
    }

    protected Table e15_1() {
        return dataManager.getTable(E15E1.class);
    }

    protected Table e15_5() {
        return dataManager.getRelatedTable(E15.class, E15.E5S);
    }

    protected Table e17() {
        return dataManager.getTable(E17.class);
    }

    protected Table e18() {
        return dataManager.getTable(E18.class);
    }

    protected Table e19() {
        return dataManager.getTable(E19.class);
    }

    protected Table e20() {
        return dataManager.getTable(E20.class);
    }

    protected Table e21() {
        return dataManager.getTable(E21.class);
    }

    protected Table e22() {
        return dataManager.getTable(E22.class);
    }

    protected Table e23() {
        return dataManager.getTable(E23.class);
    }

    protected Table e24() {
        return dataManager.getTable(E24.class);
    }

    protected Table e25() {
        return dataManager.getTable(E25.class);
    }

    protected Table e26() {
        return dataManager.getTable(E26.class);
    }

    protected Table e27NoPk() {
        return dataManager.getTable(E27Nopk.class);
    }

    public static class AgModule implements Module {

        private BiFunction<AgBuilder, ICayennePersister, AgBuilder> agCustomizer;

        public AgModule(BiFunction<AgBuilder, ICayennePersister, AgBuilder> agCustomizer) {
            this.agCustomizer = agCustomizer;
        }

        @Override
        public void configure(Binder binder) {
        }

        @Provides
        @Singleton
        AgRuntime createRuntime(ServerRuntime runtime) {
            ICayennePersister persister = new CayennePersister(runtime);
            return agCustomizer.apply(new AgBuilder().cayenneService(persister), persister).build();
        }
    }
}
