package com.garanti.repo;

import com.garanti.Constants;
import com.garanti.model.DersDTO;

import java.sql.*;
import java.util.ArrayList;

public class DersDtoRepo {

    public ArrayList<DersDTO> getAll(){
        ArrayList<DersDTO> liste = new ArrayList<>();
        Connection conn = null ;
        Statement stmt = null ;
        ResultSet result = null ;
        try {
            conn = Constants.getConnection();
            stmt = conn.createStatement();
            result=stmt.executeQuery("select d.id,ogr.name as OGRETMEN,k.name as KONU from ders d \n" +
                    "inner join ogretmen ogr on ogr.id = d.ogr_id\n" +
                    "inner join konu k on k.id = d.konu_id");

            while (result.next()){
                DersDTO temp = new DersDTO(result.getInt("ID"),result.getString("OGRETMEN"),
                        result.getString("KONU"));
                liste.add(temp);
            }

        }
        catch (Exception e){
            liste.clear();
            System.out.println(e.getMessage());
        }
        finally {

            try {
                result.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException e){

            }
        }
        return liste;
    }

    public DersDTO getById(int id){
        DersDTO dersDTO = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try{
            conn = Constants.getConnection();
            stmt = conn.prepareStatement("select d.id,ogr.name as OGRETMEN,k.name as KONU from ders d \n" +
                    "inner join ogretmen ogr on ogr.id = d.ogr_id\n" +
                    "inner join konu k on k.id = d.konu_id where d.id = ?");
            stmt.setInt(1,id);
            result = stmt.executeQuery();
            while (result.next()){
                dersDTO = new DersDTO(result.getInt("ID"),result.getString("OGRETMEN"),
                        result.getString("KONU"));
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
        return dersDTO;
    }

}
