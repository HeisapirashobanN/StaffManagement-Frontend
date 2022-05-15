package model;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.staff;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Staff")
public class Service {
	staff staffObj = new staff();
	 
	 @POST
	 @Path("/insert")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertStaff(@FormParam("name") String name,
	 @FormParam("title") String title,
	 @FormParam("mail") String mail,
	 @FormParam("contact") String contact,
	 @FormParam("gender") String gender,
	 @FormParam("password") String password)
	 
	 {
	 String output = staffObj.insertStaff(name, title, mail, contact, gender);
	 return output;
	 }
	 
	 
	 
	 @GET
	 @Path("/read")
	 @Produces(MediaType.TEXT_HTML)
	 public String readStaff()
	 {
	 return staffObj.readStaff();
	 }
	 
	 
	 
	 @PUT
	 @Path("/update")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateStaff(String staffData)
	 {
	 //Convert the input string to a JSON object
	 JsonObject StaffObject = new JsonParser().parse(staffData).getAsJsonObject();
	 //Read the values from the JSON object
	 String StaffId = StaffObject.get("StaffId").getAsString();
	 String name = StaffObject.get("name").getAsString();
	 String title = StaffObject.get("title").getAsString();
	 String mail = StaffObject.get("mail").getAsString();
	 String contact = StaffObject.get("contact").getAsString();
	 String gender = StaffObject.get("gender").getAsString();
	 String output = staffObj.updateStaff(StaffId, name, title, mail, contact, gender);
	 return output;
	 }
	 
	 
	 
	 @DELETE
	 @Path("/delete")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteStaff(String staffData)
	 {
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(staffData, "", Parser.xmlParser());
	 //Read the value from the element <itemID>
	 String StaffId = doc.select("StaffId").text();
	 String output = staffObj.deleteStaff(StaffId);
	 return output;
	 }
	 

}
