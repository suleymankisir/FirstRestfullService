package com.garanti.endpoints;

import com.garanti.model.Ders;
import com.garanti.repo.DersRepo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("dersler")
public class DersEndpoints {

    DersRepo dersRepo = new DersRepo();
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Ders> getAll(){
        return this.dersRepo.getAll();

    }
    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ders getById(@PathParam("id") int id){
        return this.dersRepo.getById(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public String save(Ders ders){
        this.dersRepo.save(ders);
        return "başarılı";
    }

}
