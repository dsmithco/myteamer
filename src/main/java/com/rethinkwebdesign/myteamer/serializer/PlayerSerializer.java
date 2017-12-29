package com.rethinkwebdesign.myteamer.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.rethinkwebdesign.myteamer.model.Player;

import java.io.IOException;

public class PlayerSerializer extends StdSerializer<Player> {

    public PlayerSerializer() {
        this(null);
    }

    public PlayerSerializer(Class<Player> t) {
        super(t);
    }

    @Override
    public void serialize(
            Player value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("name", value.getName());
        jgen.writeNumberField("team", value.getTeam().getId());
        jgen.writeEndObject();
    }
}
