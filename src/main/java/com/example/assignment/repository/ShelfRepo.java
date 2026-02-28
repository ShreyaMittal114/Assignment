package com.example.assignment.repository;

import com.example.assignment.Exception.DeviceNotFound;
import com.example.assignment.Exception.ShelfNotFound;
import com.example.assignment.Exception.ShelfOperationException;
import com.example.assignment.model.Shelf;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShelfRepo {
    private final Driver driver;

    public ShelfRepo(Driver driver) {
        this.driver = driver;
    }

    public String createShelf(Shelf shelf) {

        String query= """
                CREATE(s:Shelf{
                 name: $name,
                 partNumber : $partNumber,
                 Occupied : false,
                 Deleted : false
                 
                 })
                 RETURN elementId(s) AS shelfId
                """;

        try(Session session = driver.session()){
            return session.executeWrite(tx->{
                Result result=  tx.run(query, Values.parameters("name", shelf.getName(),"partNumber", shelf.getPartNumber()));
                return "shelf created successfully with id : " + result.single().get("shelfId").asString()  ;
            });
        }
    }

    public String occupyShelf(String shelfId, String shelfPositionId) {

        String query = """
               MATCH (s:Shelf)
WHERE elementId(s) = $shelfId
AND s.Deleted = false
AND s.Occupied = false
 
MATCH (sp:ShelfPosition)
WHERE elementId(sp) = $shelfPositionId
AND sp.Deleted = false
AND sp.Occupied = false
 
CREATE (sp)-[:HAS_SHELF]->(s)
 
SET s.Occupied = true
 SET sp.Occupied = true
 
RETURN elementId(s) AS shelfId,
       elementId(sp) AS shelfPositionId""";

        try(Session session = driver.session()){
            return session.executeWrite(tx->{
                Result result=  tx.run(query, Values.parameters("shelfId",shelfId,"shelfPositionId",shelfPositionId));

                if(!result.hasNext()){
                    throw new ShelfOperationException("shelf or shelf position not found or already occupied");
                }
                //result.single consumes the record dont call twice
                Record record=result.single();
                String sId =  record.get("shelfId").asString() ;
                String spId = record.get("shelfPositionId").asString();
                return "shelf with id : " + sId+" attached to shelf position with id : " +spId + "successfully";
            });

        }



    }

    public Shelf getShelfById(String shelfId) {
        String query= """
                MATCH(s:Shelf)
                WHERE elementId(s) = $shelfId
                AND s.Deleted=false
                RETURN s
                """;

        try(Session session = driver.session()){
            return session.executeRead(tx->{
                Result result=  tx.run(query, Values.parameters("shelfId", shelfId));
                if(!result.hasNext()){
                    throw new ShelfNotFound("There is no shelf with this id");
                }
                Record record= result.single();
                Node node=record.get("s").asNode();
                Shelf shelf = new Shelf();
                shelf.setId(node.elementId());
                shelf.setName(node.get("name").asString());
                shelf.setOccupied(node.get("Occupied").asBoolean());
                shelf.setDeleted(node.get("Deleted").asBoolean());
                return shelf;
            });
        }
    }
//    public Shelf getByName(String name) {
//
//    }

    public List<Shelf> getAllShelf() {

        String query= """
                MATCH(s:Shelf)
                WHERE s.Deleted=false
                RETURN s
                """;

        try(Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query);

                List<Shelf> shelves = new ArrayList<>();
                while (result.hasNext()) {
                    Record record = result.next();
                    Node node = record.get("s").asNode();
                    Shelf shelf = new Shelf();
                    shelf.setId(node.elementId());
                    shelf.setName(node.get("name").asString());
                    shelf.setPartNumber(node.get("partNumber").asLong(0L));//if null set part no=0
                    shelf.setOccupied(node.get("Occupied").asBoolean());
                    shelf.setDeleted(node.get("Deleted").asBoolean());
                    shelves.add(shelf);
                }
                return shelves;
            });

        }

    }


    public String UpdateShelf(String shelfId , Shelf shelf) {
        String query = """
                MATCH(s:Shelf)
                WHERE elementId(s)=$shelfId  AND s.Deleted=false
                SET s.name = $name,
                     s.partNumber = $partNumber
                RETURN s
                """;


        try (Session session = driver.session()) {
            return session.executeWrite(tx -> {
                Result result = tx.run(query, Values.parameters(
                        "shelfId",shelfId,
                        "name", shelf.getName(),
                        "partNumber", shelf.getPartNumber()

                ));
                if (!result.hasNext()) {
                    throw new ShelfNotFound("There is no shelf with this id");
                }

                return "Shelf updated Successfully";


            });


        }
    }
}
