package de.rgzm.alligator.rest;

import de.rgzm.alligator.classes.AlligatorEvent;
import de.rgzm.alligator.functions.Alligator;
import de.rgzm.alligator.functions.Timeline;
import de.rgzm.alligator.log.Logging;
import de.rgzm.alligator.restconfig.ResponseGZIP;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Path("/timeline")
public class TimelineResource {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response loadCAgetJSON(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            // parse TSV as text/plain
            String[] lines = null;
            List inputfile = new ArrayList();
            if (tsv.contains("\r\n")) {
                lines = tsv.split("\r\n");
            } else if (tsv.contains("\n")) {
                lines = tsv.split("\n");
            }
            for (String line : lines) {
                inputfile.add(line);
            }
            // init Alligator
            Alligator alligator = new Alligator();
            // create alligator events
            alligator.writeToAlligatorEventList(inputfile, null, null);
            // calculate distances
            alligator.calculateDistances();
            // calculate next fixed neighbours
            alligator.getNextFixedNeighbours();
            // output virtual years
            System.out.println("\r\n===== virtual years =====");
            for (Object event : alligator.events) {
                AlligatorEvent ae = (AlligatorEvent) event;
                System.out.println(ae.name + "\t" + String.valueOf(ae.a) + "\t" + String.valueOf(ae.b) + " " + ae.startFixed + " " + ae.endFixed);
            }
            // write timeline json
            JSONArray timelineJSON = Timeline.writeTimelineJSON(alligator);
            // output
            return ResponseGZIP.setResponse(acceptEncoding, timelineJSON.toString());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.TimelineResource"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

}
