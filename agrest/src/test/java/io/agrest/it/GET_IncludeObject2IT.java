package io.agrest.it;

import io.agrest.Ag;
import io.agrest.DataResponse;
import io.agrest.it.fixture.JerseyAndDerbyCase;
import io.agrest.it.fixture.cayenne.aggregate1.A11;
import io.agrest.it.fixture.cayenne.aggregate1.A12;
import io.agrest.it.fixture.cayenne.aggregate1.A13;
import io.agrest.it.fixture.cayenne.aggregate1.A14;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class GET_IncludeObject2IT extends JerseyAndDerbyCase {

    @BeforeClass
    public static void startTestRuntime() {
        startTestRuntime(Resource.class);
    }

    @Override
    protected Class<?>[] testEntities() {
        return new Class[]{A11.class, A12.class, A13.class, A14.class};
    }


    @Test
    public void testIncludesAndFilters() {

        a12().insertColumns("id").values(1).values(2).exec();
        a11().insertColumns("id", "a2_id").values(11, 1).values(12, 2).exec();
        a13().insertColumns("id", "a2_id", "f").values(21, 1, "a").values(22, 2, "b").exec();
        a14().insertColumns("id", "a3_id", "f").values(311, 21, "x").values(321, 22, "y").values(322, 22, "z").exec();

        Response r = target("/a11")
                .queryParam("include", "a2.a3s.a4s")
                .queryParam("include", urlEnc("{\"path\":\"a2.a3s\",\"cayenneExp\": \"(f='a') or (a4s.f='z')\"}"))
                .request().get();

        onSuccess(r).bodyEquals(2, "{\"id\":11,\"a2\":{\"a3s\":[{\"id\":21,\"a4s\":[{\"id\":311,\"f\":\"x\"}],\"f\":\"a\"}]}}," +
                "{\"id\":12,\"a2\":{\"a3s\":[{\"id\":22,\"a4s\":[{\"id\":321,\"f\":\"y\"},{\"id\":322,\"f\":\"z\"}],\"f\":\"b\"}]}}");
    }

    @Path("")
    public static class Resource {

        @Context
        private Configuration config;

        @GET
        @Path("a11")
        public DataResponse<A11> getA11(@Context UriInfo uriInfo) {
            return Ag.service(config).select(A11.class).uri(uriInfo).get();
        }
    }
}
