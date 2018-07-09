package de.rgzm.alligator.rest;

import de.rgzm.alligator.config.POM;
import de.rgzm.alligator.functions.Alligator;
import de.rgzm.alligator.functions.MatrixAllen;
import de.rgzm.alligator.functions.Timeline;
import de.rgzm.alligator.log.Logging;
import de.rgzm.alligator.restconfig.ResponseGZIP;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;

@Path("/")
public class AlligatorAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getInfo(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader) throws IOException {
        try {
            return ResponseGZIP.setResponse(acceptEncoding, POM.getInfo().toString());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }
    
    @POST
    @Path("/matrixallen")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response loadCAgetMATRIXDIST(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            
            // write timeline json
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsv);
            JSONArray matrixJSON = MatrixAllen.writeMatrixAsTSV(alligator);
            // output
            return ResponseGZIP.setResponse(acceptEncoding, matrixJSON.toString());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }
    
    @POST
    @Path("/timeline")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response loadCAgetTIMELINEJSON(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            
            // write timeline json
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsv);
            JSONArray timelineJSON = Timeline.writeTimelineJSON(alligator);
            // output
            return ResponseGZIP.setResponse(acceptEncoding, timelineJSON.toString());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

}
