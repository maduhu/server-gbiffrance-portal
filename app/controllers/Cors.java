package controllers;
 
import play.mvc.*;
 
public class Cors extends Controller{
  
    public static Result cors() {
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        return ok();
    } 
}