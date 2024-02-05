package com.zenyatta.challenge.inditex.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.zenyatta.challenge.inditex.dto.ItemResponse;
import com.zenyatta.challenge.inditex.model.Stock;
import java.io.IOException;
import java.io.Serial;
import java.lang.reflect.RecordComponent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class ItemResponseSerializer extends StdSerializer<ItemResponse> {
    @Serial
    private static final long serialVersionUID = 1234567891;

    protected ItemResponseSerializer() {
        super(ItemResponse.class);
    }

    @Override
    public void serialize(final ItemResponse response, final JsonGenerator generator,
            final SerializerProvider serializers) throws IOException {
        generator.writeStartObject();

        for (final RecordComponent component : ItemResponse.class.getRecordComponents()) {
            final String field = component.getAccessor().getName();

            if ("stock".equals(field)) {
                continue;
            }

            writeField(field, generator, response);
        }

        if (!response.stock().isEmpty()) {
            generator.writeArrayFieldStart("stock");

            for (final Stock entry : response.stock()) {
                generator.writeObject(entry);
            }

            generator.writeEndArray();
        }

        generator.writeEndObject();
    }

    private void writeField(
            final String field,
            final JsonGenerator generator,
            final ItemResponse response) throws IOException {
        try {
            final Object itemResponse = response.getClass().getDeclaredMethod(field).invoke(response);

            if (itemResponse != null) {
                generator.writeStringField(field, itemResponse.toString());
            } else {
                generator.writeNullField(field);
            }
        } catch (final Exception e) {
            throw (IOException) new IOException(e.getMessage());
        }
    }
}
