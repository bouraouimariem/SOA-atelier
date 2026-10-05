package ressourcesrest;
import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/options")
public class restOption {
    public static OptionBusiness optB = new OptionBusiness();
    //q1 et q 3 get get
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOpptions(@QueryParam("domaine") String D){
        List<Option> l = new ArrayList<Option>();
        if (D == null){
            l = optB.getListeOptions();
        }
        else{
            l = optB.getOptionsByDomaine(D);
        }
        if (l.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(200).entity(l).build();
    }
    //q2 post
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOption(Option op){
        if (optB.addOption(op)){
            return Response.status(200).build();
        }
        else {return Response.status(404).build();}
    }
    //q 4 delet
    @DELETE
    @Path("/{code}")
    public Response deleteOption(@PathParam("code") int id){
        if (optB.deleteOption(id)){
            return Response.status(204).build();
        }
        else {
            return Response.status(404).build();
        }
    }
    //q5 put
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOption(@PathParam("id") int id, Option op){
        if (optB.updateOption(id, op)){
            return Response.status(200).build();
        }
        else {
            return Response.status(404).build();
        }
    }
    //q6
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptionByCode(@PathParam("code") int code){
        Option o = optB.getOptionByCode(code);
        if (o == null){
            return Response.status(404).build();
        }
        return Response.status(200).entity(o).build();
    }

}