package com.garanti.repo;

import com.garanti.Constants;
import com.garanti.model.Konu;
import com.garanti.model.Ogrenci;

import java.sql.*;
import java.util.ArrayList;

public class KonuRepo {

    public ArrayList<Konu> getAll(){
        ArrayList<Konu> liste = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null ;
        ResultSet result = null;
        try {
            conn = Constants.getConnection();
            stmt = conn.createStatement();
            result = stmt.executeQuery("select * from BILGE.KONU");
            while (result.next()){
                Konu temp = new Konu(result.getInt("ID"),result.getString("NAME"));
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

    public Konu getById(int id){
        Konu konu = null ;
        Connection conn = null ;
        PreparedStatement stmt = null ;
        ResultSet result = null ;

        try{
            conn = Constants.getConnection();
            stmt = conn.prepareStatement("select * from BILGE.KONU where ID = ?");
            stmt.setInt(1, id);
            result = stmt.executeQuery();
            while (result.next()){
                konu = new Konu(result.getInt("ID"),result.getString("NAME"));
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

        return konu;
    }

    public boolean delete(int id){
        boolean result = false;
        Connection connection = null;
        PreparedStatement stmt = null;

        try
        {
            connection = Constants.getConnection();
            stmt = connection.prepareStatement("delete from BILGE.KONU where ID = ?");
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
    public boolean save(Konu konu)
    {
        boolean result = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        try
        {
            connection = Constants.getConnection();
            stmt = connection.prepareStatement("Insert into BILGE.KONU (NAME) values (?)");
            stmt.setString(1,konu.getNAME() );
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
