package com.garanti.endpoints;

import com.garanti.model.Ogrenci;
import com.garanti.repo.OgrenciRepo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("ogrenciler")
public class OgrenciEndpoints {

    OgrenciRepo ogrenciRepo = new OgrenciRepo();
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Ogrenci> getAll(){
        return this.ogrenciRepo.getAll();

    }
    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ogrenci getById(@PathParam("id") int id){
        return this.ogrenciRepo.getById(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public String save(Ogrenci ogrenci){
        this.ogrenciRepo.save(ogrenci);
        return "başarılı";
    }

}