package com.ps;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DynamoDBHelper {
    public static final String TABLE_NAME = "Transactions";
    private static final DynamoDbClient client = DynamoDbClient.builder().region(Region.US_EAST_1).build();

    public static void addTransaction(String date, String time, String description, String vendor, double amount){
        String transactionId = UUID.randomUUID().toString();

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("transactionId", AttributeValue.fromS(transactionId));
        item.put("date", AttributeValue.fromS(date));
        item.put("time", AttributeValue.fromS(time));
        item.put("description", AttributeValue.fromS(description));
        item.put("vendor", AttributeValue.fromS(vendor));
        item.put("amount", AttributeValue.fromN(String.valueOf(amount)));

        PutItemRequest request = PutItemRequest.builder()
                .tableName("Transactions")
                .item(item)
                .build();

        client.putItem(request);

    }

}
