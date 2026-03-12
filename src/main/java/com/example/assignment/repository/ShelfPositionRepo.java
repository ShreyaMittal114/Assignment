package com.example.assignment.repository;

import com.example.assignment.model.Shelf;
import com.example.assignment.model.ShelfPosition;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class ShelfPositionRepo {

    private final Driver driver;

    public ShelfPositionRepo(Driver driver) {
        this.driver = driver;
    }

    public List<String> createShelves(String deviceId, Long count){
        String query = """
                MATCH(d:Device)
                WHERE elementId(d) = $deviceId AND d.Deleted=false
                CREATE (d)-[:HAS_ShelfPosition]->(s:ShelfPosition {
                Occupied:false,
                Deleted:false
  })
              RETURN elementId(s) AS shelfPositionId
                """;
        List<String> shelfPositionIds = new ArrayList<>();

        try (Session session = driver.session()) {

            session.executeWrite(tx -> {

                for (int i = 0; i < count; i++) {

                    Result result = tx.run(query,
                            Values.parameters("deviceId", deviceId));

                    String shelfPositionId = result
                            .single()
                            .get("shelfPositionId")
                            .asString();

                    shelfPositionIds.add(shelfPositionId);
                }

                return null;
            });
        }

        return shelfPositionIds;
    }

    public List<ShelfPosition> getShelvesPositionByDeviceId(String deviceId) {
        String query = """
        MATCH (d:Device)-[:HAS_ShelfPosition]->(sp:ShelfPosition)
        
        WHERE elementId(d) = $deviceId
        AND d.Deleted=false
        AND sp.Deleted = false
         OPTIONAL MATCH (sp)-[:HAS_SHELF]->(sh:Shelf)
        RETURN DISTINCT sh,sp
    """;

        try (Session session = driver.session()) {

            return session.executeRead(tx -> {

                Result result = tx.run(query,
                        Values.parameters("deviceId", deviceId));

                List<ShelfPosition> shelfPositions = new ArrayList<>();

                while (result.hasNext()) {

                    Record record = result.next();

                    Node spNode = record.get("sp").asNode();

                    ShelfPosition shelfPosition = new ShelfPosition();
                    shelfPosition.setPositionId(spNode.elementId());
                    shelfPosition.setOccupied(spNode.get("Occupied").asBoolean());
                    shelfPosition.setDeleted(spNode.get("Deleted").asBoolean());

                    // Checking  if shelf exists
                    if (!record.get("sh").isNull()) {
                        Node shelfNode = record.get("sh").asNode();

                        Shelf shelf = new Shelf();
                        shelf.setId(shelfNode.elementId());
                        shelf.setName(shelfNode.get("name").asString());
                        shelf.setPartNumber(shelfNode.get("partNumber").asLong());

                        shelfPosition.setShelf(shelf);
                    }

                    shelfPositions.add(shelfPosition);
                }

                return shelfPositions;
            });
        }
    }
}


