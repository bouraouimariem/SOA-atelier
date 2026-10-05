package ressourcesrest;

import entities.Etudiant;
import entities.EtudiantList;
import entities.Option;
import metiers.EtudiantBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/etudiants")
public class restEtudiant {
    public static EtudiantBusiness etB = new EtudiantBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant e){
        if (restOption.optB.getOptionByCode(e.getOption().getCodeOption()) == null){
            return Response.status(404).build();
        }
        else{
            etB.addEtudiant(e);
            return Response.status(200).build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtudiants(){
        List<Etudiant> l = etB.getAllEtudiants();
        return Response.status(200).entity(l).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantById(@PathParam("id") String id){
        Etudiant e = etB.getEtudiantByIdentifiant(id);
        if (e == null){
            return Response.status(404).build();
        }
        else {
            return Response.status(200).entity(e).build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response deleteEtudiant(@PathParam("id") String id){
        if (etB.deleteEtudiant(id)){
            return Response.status(204).build();
        }
        return Response.status(404).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(@PathParam("id") String id, Etudiant e){
        if (etB.updateEtudiant(id, e)){
            return Response.status(200).build();
        }
        else {
             return Response.status(404).build();
        }
    }
    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(@QueryParam("codeOption") int codeOption){
        Option o = restOption.optB.getOptionByCode(codeOption);
        if (o == null){
            return Response.status(404).build();
        }
        else {
            List<Etudiant> l = etB.getEtudiantsByOption(o);
            EtudiantList el = new EtudiantList(l);
            return Response.status(200).entity(el).build();
        }
    }
}