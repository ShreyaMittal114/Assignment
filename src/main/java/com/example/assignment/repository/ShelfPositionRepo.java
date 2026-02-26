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
                WHERE elementId(d) = $deviceId
                CREATE (d)-[:HAS_SHELF]->(s:ShelfPosition {
                isOccupied:false,
                isDeleted:false
  })
              RETURN elementId(s) AS shelfId
                """;
        List<String> shelfIds = new ArrayList<>();

        try (Session session = driver.session()) {

            session.executeWrite(tx -> {

                for (int i = 0; i < count; i++) {

                    Result result = tx.run(query,
                            Values.parameters("deviceId", deviceId));

                    String shelfId = result
                            .single()
                            .get("shelfId")
                            .asString();

                    shelfIds.add(shelfId);
                }

                return null;
            });
        }

        return shelfIds;
    }

    public List<ShelfPosition> getShelvesByDeviceId(String deviceId) {
        String query = """
        MATCH (d:Device)-[:HAS_SHELF]->(s:ShelfPosition)
        WHERE elementId(d) = $deviceId
        AND s.isDeleted = false
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

                    shelfPosition.setisOccupied(node.get("isOccupied").asBoolean());
                    shelfPosition.setisDeleted(node.get("isDeleted").asBoolean());

                    shelfPositions.add(shelfPosition);
                }

                return shelfPositions;
            });
        }
    }
}


