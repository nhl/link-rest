package com.nhl.link.rest.encoder;

import org.junit.Test;

import java.util.Date;

import static com.nhl.link.rest.encoder.Encoders.toJson;
import static org.junit.Assert.assertEquals;

public class ISODateEncoderTest {

    private Encoder encoder = ISODateEncoder.encoder();

    @Test
    public void testISODateEncoder() {
        assertEquals("\"2016-03-26\"", toJson(encoder, new Date(1458995247000L)));
    }
}
