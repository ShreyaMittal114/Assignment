package com.example.assignment.repository;

import com.example.assignment.model.Shelf;
import com.example.assignment.model.ShelfPosition;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
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
        MATCH (d:Device)-[:HAS_ShelfPosition]->(s:ShelfPosition)
        WHERE elementId(d) = $deviceId
        AND d.Deleted=false
        AND s.Deleted = false
        RETURN s
    """;

        try (Session session = driver.session()) {

            return session.executeRead(tx -> {

                Result result = tx.run(query,
                        Values.parameters("deviceId", deviceId));

                List<ShelfPosition> shelfPositions = new ArrayList<>();

                while (result.hasNext()) {

                    Node node = result.next().get("s").asNode();

                    ShelfPosition shelfPosition = new ShelfPosition();
                    shelfPosition.setPositionId(node.elementId());

                    shelfPosition.setOccupied(node.get("Occupied").asBoolean());
                    shelfPosition.setDeleted(node.get("Deleted").asBoolean());

                    shelfPositions.add(shelfPosition);
                }

                return shelfPositions;
            });
        }
    }
}


