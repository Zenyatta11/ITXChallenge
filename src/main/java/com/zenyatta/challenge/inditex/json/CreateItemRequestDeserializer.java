package com.zenyatta.challenge.inditex.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.zenyatta.challenge.inditex.dto.CreateItemRequest;
import com.zenyatta.challenge.inditex.model.Stock;
import java.io.IOException;
import java.io.Serial;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class CreateItemRequestDeserializer extends StdDeserializer<CreateItemRequest> {
    @Serial
    private static final long serialVersionUID = 1234567890;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    protected CreateItemRequestDeserializer() {
        super(CreateItemRequest.class);
    }

    @Override
    public CreateItemRequest deserialize(final JsonParser parser, final DeserializationContext ctxt)
            throws IOException {
        final JsonNode node = parser.getCodec().readTree(parser);
        final CreateItemRequest.CreateItemRequestBuilder builder = CreateItemRequest.builder()
                .name(parseString("name", node))
                .salesUnit(parseLong("salesUnit", node))
                .stock(parseStock(node));

        return builder.build();
    }

    private String parseString(final String field, final JsonNode node) {
        return null != node.get(field) ? node.get(field).asText() : null;
    }

    private Long parseLong(final String field, final JsonNode node) {
        return null != node.get(field) ? node.get(field).asLong(0) : null;
    }

    private Set<Stock> parseStock(final JsonNode node)
            throws JsonProcessingException, JsonMappingException {
        Set<Stock> stock = null;

        if (node.get("stock") != null && !node.get("stock").isEmpty()) {
            final ArrayNode object = (ArrayNode) node.get("stock");
            final Stock[] parsedStock = MAPPER.readValue(object.toString(), Stock[].class);
            stock = new HashSet<>(Arrays.asList(parsedStock));
        }

        return stock;
    }
}
