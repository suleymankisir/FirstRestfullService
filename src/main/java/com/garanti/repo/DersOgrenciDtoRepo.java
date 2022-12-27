package com.garanti.repo;

import com.garanti.Constants;
import com.garanti.model.Ders_OgrenciDTO;

import java.sql.*;
import java.util.ArrayList;

public class DersOgrenciDtoRepo {

    public ArrayList<Ders_OgrenciDTO> getAll(){
        ArrayList<Ders_OgrenciDTO> liste = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        try {
            conn = Constants.getConnection();
            stmt = conn.createStatement();
            result=stmt.executeQuery("select do.id,o.name as OGRETMEN_NAME,k.name as KONU,og.name as OGRENCI_NAME,do.note,do.devamsızlık from ders_ogrenci do \n" +
                    "inner join ders d on d.id = do.ders_id\n" +
                    "inner join konu k on k.id = d.konu_id\n" +
                    "inner join ogretmen o on o.id = d.ogr_id\n" +
                    "inner join ogrenci og on og.id = do.ogrenci_id");
            while (result.next()){
                Ders_OgrenciDTO temp = new Ders_OgrenciDTO(result.getInt("ID"),
                        result.getString("OGRETMEN_NAME"),result.getString("KONU"),
                        result.getString("OGRENCI_NAME"),result.getInt("NOTE"),result.getInt("DEVAMSIZLIK"));
                liste.add(temp);
            }

        }
        catch (Exception e){
            liste.clear();
            System.out.println(e.getMessage());
        }
        finally {
            try{
                result.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException e){

            }
        }
        return liste;
    }

    public Ders_OgrenciDTO getById(int id){
        Ders_OgrenciDTO dersOgrenciDTO = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            conn = Constants.getConnection();
            stmt = conn.prepareStatement("select do.id,o.name as OGRETMEN_NAME,k.name as KONU,og.name as OGRENCI_NAME,do.note,do.devamsızlık from ders_ogrenci do \n" +
                    "inner join ders d on d.id = do.ders_id\n" +
                    "inner join konu k on k.id = d.konu_id\n" +
                    "inner join ogretmen o on o.id = d.ogr_id\n" +
                    "inner join ogrenci og on og.id = do.ogrenci_id where do.id = ?");
            stmt.setInt(1,id);
            result=stmt.executeQuery();
            while (result.next()){
                dersOgrenciDTO = new Ders_OgrenciDTO(result.getInt("ID"),
                        result.getString("OGRETMEN_NAME"),result.getString("KONU"),
                        result.getString("OGRENCI_NAME"),result.getInt("NOTE"),result.getInt("DEVAMSIZLIK"));

            }

        }
        catch (Exception e){

            System.out.println(e.getMessage());
        }
        finally {
            try{
                result.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException e){

            }
        }
        return dersOgrenciDTO;
    }

}
