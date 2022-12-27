package com.garanti.endpoints;

import com.garanti.model.Ogretmen;
import com.garanti.repo.OgretmenRepo;

import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("ogretmenler")
public class OgretmenEndpoints {
    OgretmenRepo ogretmenRepo = new OgretmenRepo();
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Ogretmen> getAll(){
        return this.ogretmenRepo.getAll();

    }
    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ogretmen getById(@PathParam("id") int id){
        return this.ogretmenRepo.getById(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public String save(Ogretmen ogretmen){
        this.ogretmenRepo.save(ogretmen);
        return "başarılı";
    }

}
