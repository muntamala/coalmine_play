package controllers;

import java.util.*;
import play.*;
import play.mvc.*;
import models.*;

public class Application extends Controller {
    public static void index() {
        render();
    }
    
    public static void exception() {
        try {
            throw new Exception("Play Exception");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
