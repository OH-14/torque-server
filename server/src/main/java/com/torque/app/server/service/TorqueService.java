// Indicate to java that this is part of the proyect
package com.torque.app.server.service;
//to be able to use @service
import org.springframework.stereotype.Service;
// Tells java that this class is a service meaning that it manages logic and 
// that if someone request it it can use it
@Service
public class TorqueService {
    //test function to test if server is working
    public String proccessData(String entrada){
        return "El servidor procesó tu mensaje: "+entrada; 
    }
}