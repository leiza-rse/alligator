package de.rgzm.alligator.rest;

import de.rgzm.alligator.config.POM;
import de.rgzm.alligator.functions.AMTEvents;
import de.rgzm.alligator.functions.Alligator;
import de.rgzm.alligator.functions.Cypher;
import de.rgzm.alligator.functions.Graph;
import de.rgzm.alligator.functions.MatrixAllen;
import de.rgzm.alligator.functions.MatrixDist;
import de.rgzm.alligator.functions.RDFEvents;
import de.rgzm.alligator.functions.Timeline;
import de.rgzm.alligator.log.Logging;
import de.rgzm.alligator.restconfig.ResponseGZIP;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    public Response loadCAgetMATRIXALLEN(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            String[] tsvsplit = tsv.split(";");
            String[] fixedSplit = tsvsplit[0].split(",");
            Double startFixedValue = null;
            Double endFixedValue = null;
            if (fixedSplit.length == 2) {
                startFixedValue = Double.parseDouble(fixedSplit[0]);
                endFixedValue = Double.parseDouble(fixedSplit[1]);
            }
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsvsplit[1], startFixedValue, endFixedValue);
            JSONArray matrixJSON = MatrixAllen.writeMatrixAsJSONArray(alligator);
            return ResponseGZIP.setResponse(acceptEncoding, matrixJSON.toString());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

    @POST
    @Path("/matrixdist")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response loadCAgetMATRIXDIST(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            String[] tsvsplit = tsv.split(";");
            String[] fixedSplit = tsvsplit[0].split(",");
            Double startFixedValue = null;
            Double endFixedValue = null;
            if (fixedSplit.length == 2) {
                startFixedValue = Double.parseDouble(fixedSplit[0]);
                endFixedValue = Double.parseDouble(fixedSplit[1]);
            }
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsvsplit[1], startFixedValue, endFixedValue);
            JSONArray matrixJSON = MatrixDist.writeMatrixAsJSONArray(alligator);
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
            String[] tsvsplit = tsv.split(";");
            String[] fixedSplit = tsvsplit[0].split(",");
            Double startFixedValue = null;
            Double endFixedValue = null;
            if (fixedSplit.length == 2) {
                startFixedValue = Double.parseDouble(fixedSplit[0]);
                endFixedValue = Double.parseDouble(fixedSplit[1]);
            }
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsvsplit[1], startFixedValue, endFixedValue);
            JSONArray timelineJSON = Timeline.writeTimelineJSON(alligator);
            return ResponseGZIP.setResponse(acceptEncoding, timelineJSON.toString());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

    @POST
    @Path("/graph")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response loadCAgetGRAPHJSON(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            String[] tsvsplit = tsv.split(";");
            String[] fixedSplit = tsvsplit[0].split(",");
            Double startFixedValue = null;
            Double endFixedValue = null;
            if (fixedSplit.length == 2) {
                startFixedValue = Double.parseDouble(fixedSplit[0]);
                endFixedValue = Double.parseDouble(fixedSplit[1]);
            }
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsvsplit[1], startFixedValue, endFixedValue);
            JSONObject graphJSON = Graph.writeGraphJSON(alligator);
            return ResponseGZIP.setResponse(acceptEncoding, graphJSON.toString());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

    @POST
    @Path("/turtle")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public Response loadCAgetRDFFILE(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            String[] tsvsplit = tsv.split(";");
            String[] fixedSplit = tsvsplit[0].split(",");
            Double startFixedValue = null;
            Double endFixedValue = null;
            if (fixedSplit.length == 2) {
                startFixedValue = Double.parseDouble(fixedSplit[0]);
                endFixedValue = Double.parseDouble(fixedSplit[1]);
            }
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsvsplit[1], startFixedValue, endFixedValue);
            String rdf = RDFEvents.writeRDFasText(alligator);
            return Response.ok(rdf).header("Content-Type", "text/plain;charset=UTF-8").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }
    
    @POST
    @Path("/cypher")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public Response loadCAgetCYPHERFILE(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            String[] tsvsplit = tsv.split(";");
            String[] fixedSplit = tsvsplit[0].split(",");
            Double startFixedValue = null;
            Double endFixedValue = null;
            if (fixedSplit.length == 2) {
                startFixedValue = Double.parseDouble(fixedSplit[0]);
                endFixedValue = Double.parseDouble(fixedSplit[1]);
            }
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsvsplit[1], startFixedValue, endFixedValue);
            String cypher = Cypher.writeCypher(alligator);
            return Response.ok(cypher).header("Content-Type", "application/x-cypher-query;charset=UTF-8").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }
    
    @POST
    @Path("/amt")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public Response loadCAgetAMTFILE(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            String[] tsvsplit = tsv.split(";");
            String[] fixedSplit = tsvsplit[0].split(",");
            Double startFixedValue = null;
            Double endFixedValue = null;
            if (fixedSplit.length == 2) {
                startFixedValue = Double.parseDouble(fixedSplit[0]);
                endFixedValue = Double.parseDouble(fixedSplit[1]);
            }
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsvsplit[1], startFixedValue, endFixedValue);
            String rdf = AMTEvents.writeRDFAsText(alligator);
            return Response.ok(rdf).header("Content-Type", "text/plain;charset=UTF-8").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }
    
    @POST
    @Path("/amtrepo")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public Response loadCAgetAMTREPO(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            String[] tsvsplit = tsv.split(";");
            String[] fixedSplit = tsvsplit[0].split(",");
            Double startFixedValue = null;
            Double endFixedValue = null;
            if (fixedSplit.length == 2) {
                startFixedValue = Double.parseDouble(fixedSplit[0]);
                endFixedValue = Double.parseDouble(fixedSplit[1]);
            }
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsvsplit[1], startFixedValue, endFixedValue);
            String url = AMTEvents.writeRDFInRDF4JRepository(alligator);
            return Response.ok(url).header("Content-Type", "text/plain;charset=UTF-8").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

    @POST
    @Path("/turtlefile")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces("text/turtle;charset=UTF-8")
    public Response loadCAgetRDF(@HeaderParam("Accept-Encoding") String acceptEncoding, @HeaderParam("Accept") String acceptHeader, String tsv) throws IOException {
        try {
            String[] tsvsplit = tsv.split(";");
            String[] fixedSplit = tsvsplit[0].split(",");
            Double startFixedValue = null;
            Double endFixedValue = null;
            if (fixedSplit.length == 2) {
                startFixedValue = Double.parseDouble(fixedSplit[0]);
                endFixedValue = Double.parseDouble(fixedSplit[1]);
            }
            Alligator alligator = new Alligator();
            alligator = alligator.calculate(tsvsplit[1], startFixedValue, endFixedValue);
            String rdf = RDFEvents.writeRDFasText(alligator);
            String filename = String.valueOf(System.currentTimeMillis()) + ".ttl";
            //String filenpath = "C://tmp/alligator-files/" + filename;
            String filenpath = "/opt/tomcat/webapps/alligator-files/"  + filename;
            try {
                File fileDir = new File(filenpath);
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF8"));
                out.append(rdf);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.toString();
            }
            return Response.ok(filename).header("Content-Type", "text/plain;charset=UTF-8").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Logging.getMessageJSON(e, "de.rgzm.alligator.rest.AlligatorAPI"))
                    .header("Content-Type", "application/json;charset=UTF-8").build();
        }
    }

}
