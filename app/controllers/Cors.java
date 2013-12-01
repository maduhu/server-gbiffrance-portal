package controllers;

import play.mvc.*;

public class Cors extends Controller{

    public static Result cors() {
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        return ok();
    }
    public static Result cors1(String params) {
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        return ok();
    }
    public static Result cors2(String param1, String param2) {
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        return ok();
    }
    public static Result cors3(String param1, String param2, String param3) {
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        return ok();
    }
    public static Result cors4(String param1, String param2, String param3, String param4) {
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Allow-Headers", "Content-Type");
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        return ok();
    }
}


