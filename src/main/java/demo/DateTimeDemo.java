package demo;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateTimeDemo {
    
    public static void main(String[] args) {
        Instant instant = Instant.now();
        OffsetDateTime dateTime = instant.atOffset(ZoneOffset.UTC);
        
        System.out.println(dateTime);
    }
}
