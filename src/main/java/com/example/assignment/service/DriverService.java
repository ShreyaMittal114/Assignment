package com.example.assignment.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

@Service
public class DriverService   {
    private final Driver driver;
    public DriverService(Driver driver){
        this.driver=driver;
    }

    public String testConnection(){
        try(Session session = driver.session()){
            return session.run("RETURN 'success' AS msg")
                    .single()
                    .get("msg")
                    .asString();

        }
        catch(Exception e){
            return "connection failed : " + e.getMessage();
        }
    }


}
