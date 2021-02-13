/*
    Classname: StoreCharacters
    @Authors: Asmita Hari, Anmol Jaising.
 */

 /*
    This class is used to establish a connection and
    get the url and read the contents of the url
     and download the file.

 */

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;

public class PostgresExample {

    private final String DatabaseURL = "jdbc:postgresql://reddwarf.cs.rit.edu/";
    private final String user = "csci605";
    private final String password = "sometables";

    /*
        This method is used to connect to the server.
        Once we connect to the database, we create a query that helps us obtain
        the url to get.
    */   
    
    public String connect() {
        Connection conn = null;
        Statement  statement =null;
        String urltoGet="";
        try {
            conn = DriverManager.getConnection(DatabaseURL, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");

            Class.forName("org.postgresql.Driver");

            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery( "SELECT to_parse FROM sites;" );
            while ( rs.next() ) {

                urltoGet=rs.getString("to_parse");
                System.out.println( "to_parse = " +  urltoGet );
                System.out.println();
            }
            rs.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return urltoGet;
    }
    
    /*
        Having the URL, we open a connection and create an input stream.
        Once this stream this open, we read all the contents of the file.
        If we find the "hidden_html", we get that line, split it on "="
        and replace "-->" by "".
        
        This is then sent to the method that downloads the file.
    
    */

    public BufferedReader getConentsofaFile(String url) {
        URL urlTogetData = null;
        URLConnection urlConnection = null;
        BufferedReader bufferedReader=null;

        try {
            urlTogetData = new URL(url);
            urlConnection = urlTogetData.openConnection();
            bufferedReader = new BufferedReader
                    (new InputStreamReader(urlConnection.getInputStream()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }

    /*you will parse the resulting URL and find the hidden html
    found in the source code of that html. */
    public String parseURL(String url){
       String filename="";
       try {

             String line;
             BufferedReader bufferedReader=getConentsofaFile(url);
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                if(line.contains("hidden_html")){
                    filename=line;
                    break;
                }

            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         String[] urlToDownload= filename.split("=");
         String fileToDownload= urlToDownload[1];
         fileToDownload=fileToDownload.replaceAll("-->","");
         System.out.println(fileToDownload);

        return fileToDownload;
    }

    /*
        This file is used to download the file using the given part of the URL.
    */
    
    public String downloadFile(String url){

        String fileName=url.substring(url.lastIndexOf("/")+1);

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream= new FileOutputStream(fileName);
          ){

            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {

        }

       // fileName=fileName.split(".java")[0];
        return fileName;
    }

    /*
        This method is used to print the  remaining lines.
    */
    
    public void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
    }
    public String execClass(String fileName){
        Process pro = null;
        try {
            pro = Runtime.getRuntime().exec("javac "+fileName);

            printLines("javac" + " stdout:", pro.getInputStream());
            printLines("javac" + " stderr:", pro.getErrorStream());

            pro.waitFor();
            pro = Runtime.getRuntime().exec("java "+fileName.split(".java")[0]);

            printLines("java" + " Output:", pro.getInputStream());
            printLines("java" + " Output:", pro.getErrorStream());

            pro.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {
        PostgresExample eg= new PostgresExample();
        String urlToGet= eg.connect();
        String fileToDownload=eg.parseURL(urlToGet);
        String className= eg.downloadFile(fileToDownload);
        System.out.println(className);
        eg.execClass(className);

    }

}
