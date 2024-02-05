package com.zenyatta.challenge.inditex.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.zenyatta.challenge.inditex.dto.UpdateItemRequest;
import com.zenyatta.challenge.inditex.model.Stock;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UpdateItemRequestDeserializer extends StdDeserializer<UpdateItemRequest> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    protected UpdateItemRequestDeserializer() {
        super(UpdateItemRequest.class);
    }

    @Override
    public UpdateItemRequest deserialize(final JsonParser parser, final DeserializationContext ctx) throws IOException {
        final JsonNode node = parser.getCodec().readTree(parser);

        final UpdateItemRequest.UpdateItemRequestBuilder builder = UpdateItemRequest.builder()
                .id(parseLong("id", node))
                .name(parseField("name", node))
                .salesUnit(parseLong("sales_unit", node))
                .stock(parseStock(node));

        return builder.build();
    }

    private String parseField(final String field, final JsonNode node) {
        return node.get(field) != null ? node.get(field).asText() : null;
    }

    private Long parseLong(final String field, final JsonNode node) {
        return node.get(field) != null ? node.get(field).asLong() : null;
    }

    private Set<Stock> parseStock(final JsonNode node)
            throws JsonProcessingException, JsonMappingException {
        Set<Stock> skills = null;

        if (node.get("stock") != null && !node.get("stock").isEmpty()) {
            final ArrayNode object = (ArrayNode) node.get("stock");
            final Stock[] parsedStock = MAPPER.readValue(object.toString(), Stock[].class);
            skills = new HashSet<>(Arrays.asList(parsedStock));
        }

        return skills;
    }
}
