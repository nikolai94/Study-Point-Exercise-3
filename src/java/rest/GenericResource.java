/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * REST Web Service
 *
 * @author nikolai
 */
@Path("")
public class GenericResource {
    
    @Context
    private UriInfo context;
    private ArrayList<Player> arr = new ArrayList<Player>(); 
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        if(arr.isEmpty()){
            Player p = new Player(1,"James Rodríguez","Columbia");
            arr.add(p);
            Player p1 = new Player(2,"Thomas Mueller","Tyskland");
            arr.add(p1);
            Player p2 = new Player(3,"Messi","Argentina");
            arr.add(p2);
             Player p3 = new Player(4,"Neymar","Brasilien");
            arr.add(p3);
             Player p4 = new Player(5,"van Persie","Holland");
            arr.add(p4);
            
            
            
            //arr.add()
        }
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     * @return an instance of java.lang.String
     */
    @Path("AllPlayerNames")
    @GET
    @Produces("application/json")
    public String getJson() {
        Gson gson = new Gson();
        String clubsJson = gson.toJson(arr);
        return clubsJson;
    }
    
    @GET
    @Produces("application/json")
    @Path("player/{id}")
    public String getJsonPlayer(@PathParam("id") int id) {
        
        
      String clubsJson ="{\"errCode\": 404, \"errMsg\" : \"No player found with the given ID\" }";
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).getId() == id)
            {
                  Gson gson = new Gson();
                  Player p = arr.get(i);
                  clubsJson = gson.toJson(p);
             }    
        }
        
        return clubsJson;
    }
    
    
    
    @Path("AllPlayerFromWeb")
    @GET
    @Produces("application/json")
    public String getJsonNewPlayers()  {
        
        URL url;
         String jsonStr=null;
        try {
            
            url = new URL("http://footballpool.dataaccess.eu/data/info.wso/AllPlayerNames/JSON/debug?bSelected");
            URLConnection con = url.openConnection();
            Scanner scan = new Scanner(con.getInputStream());
           
            if (scan.hasNext()) {
             jsonStr = scan.nextLine();
            }
            System.out.println(jsonStr);
            scan.close();
            
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
       /* Gson gson = new Gson();
        String clubsJson = gson.toJson(arr);
        return clubsJson;*/
        return jsonStr;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
