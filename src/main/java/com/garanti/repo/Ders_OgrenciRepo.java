package com.garanti.repo;

import com.garanti.Constants;
import com.garanti.model.Ders_Ogrenci;
import com.garanti.model.Ogrenci;

import java.sql.*;
import java.util.ArrayList;

public class Ders_OgrenciRepo {

    public ArrayList<Ders_Ogrenci> getAll() {

        ArrayList<Ders_Ogrenci> liste = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null;
        try {
            con = Constants.getConnection();
            stmt = con.createStatement();
            result = stmt.executeQuery("select * from BILGE.DERS_OGRENCI");
            while (result.next()) {
                Ders_Ogrenci temp = new Ders_Ogrenci(result.getInt("ID"),result.getInt("DERS_ID"),
                        result.getInt("OGRENCI_ID"),result.getInt("NOTE"),result.getInt("DEVAMSIZLIK"));
                liste.add(temp);
            }
        } catch (Exception e) {
            liste.clear();
            System.out.println(e.getMessage());
        }
        finally {
            try{
                result.close();
                stmt.close();
                con.close();
            }
            catch (SQLException e){

            }

        }
        return liste;
    }

    public Ders_Ogrenci getById(int id){
        Ders_Ogrenci dersOgrenci=null;
        Connection conn = null ;
        PreparedStatement stmt = null;
        ResultSet result = null;

        try{
            conn = Constants.getConnection();
            stmt = conn.prepareStatement("select * from BILGE.DERS_OGRENCI where ID = ?");
            stmt.setInt(1,id);
            result = stmt.executeQuery();
            while (result.next()){
                dersOgrenci= new Ders_Ogrenci(result.getInt("ID"),result.getInt("DERS_ID"),
                        result.getInt("OGRENCI_ID"),result.getInt("NOTE"),result.getInt("DEVAMSIZLIK"));
            }
        }
        catch (Exception e){

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

        return dersOgrenci;
    }
    public boolean delete(int id){
        boolean result = false;
        Connection connection = null;
        PreparedStatement stmt = null;

        try
        {
            connection = Constants.getConnection();
            stmt = connection.prepareStatement("delete from BILGE.DERS_OGRENCI where ID = ?");
            stmt.setInt(1, id);
            result = stmt.executeUpdate() == 1;

        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {

                stmt.close();
                connection.close();
            }
            catch (SQLException e)
            {
                // throw new mybussinessexception()
            }
        }
        return result;
    }
    public boolean save(Ders_Ogrenci ders_ogrenci)
    {
        boolean result = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        try
        {
            connection = Constants.getConnection();
            stmt = connection.prepareStatement("Insert into BILGE.DERS_OGRENCI (DERS_ID, OGRENCI_ID,NOTE,DEVAMSIZLIK) values (?, ?, ?,?)");
            stmt.setInt(1, ders_ogrenci.getDERS_ID());
            stmt.setInt(2, ders_ogrenci.getOGRENCI_ID());
            stmt.setInt(3,ders_ogrenci.getNOTE());
            stmt.setInt(4,ders_ogrenci.getDEVAMSIZLIK());
            result = stmt.executeUpdate() == 1;
        }
        catch (SQLException e)
        {
            System.err.println("-> " + e.getClass().getName());
            System.err.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("---> " + e.getClass().getName());
        }
        finally
        {
            try
            {
                stmt.close();
                connection.close();
            }
            catch (Exception e)
            {
            }
        }
        return result;
    }

}
