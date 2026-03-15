//Indicates java that this is part of the project
package com.torque.app.server.controller;
//imports
import com.torque.app.server.service.TorqueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Tells java that this class is a rest controller meaning that it is 
// waiting to comunicate with the browser
@RestController
public class TorqueController {
//Creates an inmutable variable where the service is going to be stored
   private final TorqueService torqueService;

   //Connects the controller with the server, when the controller
   // is initialized this connects it to the server
   public TorqueController(TorqueService service){
    this.torqueService = service;
   }

   //Tells that this code is going to be executed when someone 
   //enters to /test01 path in the browser
   //the browser gets info from the server
   @GetMapping("/test01")
//request param request a string to the user
   public String test01ServerAndController(@RequestParam String param) {
    //returns another string
       return torqueService.proccessData(param);
   }
   //example of the test01 in http://localhost:8080/test01?param=HolaMundo param= "HolaMundo"
   

}
