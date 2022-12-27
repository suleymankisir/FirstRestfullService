package com.garanti.endpoints;

import com.garanti.model.Ders_Ogrenci;
import com.garanti.repo.Ders_OgrenciRepo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("dersogrenci")
public class DersOgrenciEndpoints {

    Ders_OgrenciRepo dersOgrenciRepo = new Ders_OgrenciRepo();
    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Ders_Ogrenci> getAll(){
        return this.dersOgrenciRepo.getAll();

    }
    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ders_Ogrenci getById(@PathParam("id") int id){
        return this.dersOgrenciRepo.getById(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public String save(Ders_Ogrenci ders_ogrenci){
        this.dersOgrenciRepo.save(ders_ogrenci);
        return "başarılı";
    }

}
