package de.rgzm.alligator.rest;

import de.rgzm.alligator.config.POM;
import de.rgzm.alligator.log.Logging;
import de.rgzm.alligator.restconfig.ResponseGZIP;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RootResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getInfo(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader) throws IOException {
        try {
            return ResponseGZIP.setResponse(acceptEncoding, POM.getInfo().toString());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.RootResource"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

}
