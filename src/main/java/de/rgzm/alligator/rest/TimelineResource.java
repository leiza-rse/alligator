package de.rgzm.alligator.rest;

import de.rgzm.alligator.log.Logging;
import de.rgzm.alligator.restconfig.ResponseGZIP;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

@Path("/timeline")
public class TimelineResource {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response loadCAgetJSON(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            String[] lines = null;
            if (tsv.contains("\r\n")) {
                lines = tsv.split("\r\n");
            } else if (tsv.contains("\n")) {
                lines = tsv.split("\n");
            }
            JSONObject a = new JSONObject();
            a.put("a", lines[0]);
            return ResponseGZIP.setResponse(acceptEncoding, a.toString());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.TimelineResource"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

}
