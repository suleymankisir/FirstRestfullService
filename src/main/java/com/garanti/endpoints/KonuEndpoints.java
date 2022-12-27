package com.garanti.endpoints;

import com.garanti.model.Konu;
import com.garanti.repo.KonuRepo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("konular")
public class KonuEndpoints {
    KonuRepo konuRepo = new KonuRepo();
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Konu> getAll(){
        return this.konuRepo.getAll();

    }
    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Konu getById(@PathParam("id") int id){

        return this.konuRepo.getById(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public String save(Konu konu){
        this.konuRepo.save(konu);
        return "başarılı";
    }

}
