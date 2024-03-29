package sn.ismonline.personneapp;
import java.sql.*;

import static java.lang.Class.forName;

public class DbConnexion {
    private Connection cnx;
    private PreparedStatement psts ;
    private ResultSet rs ;
    private int  ok ;


    public void getConnection(){
        String host = "localhost";
        int port = 3306;
        String database = "personne_db";
        String user = "root";
        String password = "" ;
        String url = "jdbc:mysql://"+host+":"+port+"/"+database;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx =DriverManager.getConnection(url, user, password);
            System.out.println("Connexion ok");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void initPrepar(String sql){
        try{
            getConnection();
            psts = cnx.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet executeSelect(){
        rs = null;
        try{
            rs = psts.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public int executeMaj() {
        try {
            ok = psts.executeUpdate();
            psts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

        public void closeConnection( ) {
            try{
                if(cnx != null)
                    cnx.close();
            }catch(Exception e ){
                e.printStackTrace();

            }
        }


        public PreparedStatement getPsts(){
            return psts;
        }
    }



