package com.nhl.link.rest.swagger.api.v1.service;

import com.nhl.link.rest.it.fixture.cayenne.*;
import com.nhl.link.rest.it.fixture.cayenne.E5;

import com.nhl.link.rest.DataResponse;

import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.nhl.link.rest.constraints.Constraint;
import com.nhl.link.rest.LinkRest;
import com.nhl.link.rest.SimpleResponse;

@Path("/")
public class E5Resource {

    @Context
    private Configuration config;

    @POST
    @Path("/v1/e5")
    @Consumes({ "application/json" })
    public DataResponse<E5> create(String e5) {

        return LinkRest.create(E5.class, config)
                    .readConstraint(Constraint.excludeAll(E5.class).includeId().attributes("name", "date")

                    )
                    

                    .syncAndSelect(e5);
    }

    @GET
    @Path("/v1/e5")
    @Produces({ "application/json" })
    public DataResponse<E5> getAll() {
        return LinkRest.select(E5.class, config)
                    .constraint(Constraint.excludeAll(E5.class).includeId().attributes("name", "date")

                    )
                    

                    .get();
    }

}