package com.garanti.repo;

import com.garanti.Constants;
import com.garanti.model.Ders;
import com.garanti.model.Ogrenci;

import java.sql.*;
import java.util.ArrayList;

public class DersRepo {

    public ArrayList<Ders> getAll(){
        ArrayList<Ders> liste = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null ;
        ResultSet result = null ;
        try{
            conn = Constants.getConnection();
            stmt = conn.createStatement();
            result = stmt.executeQuery("select * from BILGE.DERS");
            while (result.next()){
                Ders temp = new Ders(result.getInt("ID"),result.getInt("OGR_ID"),result.getInt("KONU_ID"));
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

    public Ders getById(int id){
        Ders ders = null;
        Connection conn = null;
        PreparedStatement stmt = null ;
        ResultSet result = null;
        try {
            conn = Constants.getConnection();
            stmt = conn.prepareStatement("select * from BILGE.DERS where ID = ?");
            stmt.setInt(1,id);
            result = stmt.executeQuery();
            while (result.next()){
                ders = new Ders(result.getInt("ID"),result.getInt("OGR_ID"),
                        result.getInt("KONU_ID"));
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
        return ders;
    }

    public boolean delete(int id){
        boolean result = false;
        Connection connection = null;
        PreparedStatement stmt = null;

        try
        {
            connection = Constants.getConnection();
            stmt = connection.prepareStatement("delete from BILGE.DERS where ID = ?");
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
    public boolean save(Ders ders)
    {
        boolean result = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        try
        {
            connection = Constants.getConnection();
            stmt = connection.prepareStatement("Insert into BILGE.DERS (KONU_ID,OGR_ID) values (?, ?)");
            stmt.setInt(1, ders.getKONU_ID());
            stmt.setInt(2, ders.getOGR_ID());
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
