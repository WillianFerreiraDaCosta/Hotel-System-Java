package hotel.utils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class UniqueIdGenerator {

    private static final AtomicInteger intCounter = new AtomicInteger(0);
    private static final AtomicLong longCounter = new AtomicLong(0);

    private UniqueIdGenerator() {
    }

    public static String generateUniqueStringId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static int generateUniqueIntId() {
        return intCounter.incrementAndGet();
    }

    public static long generateUniqueLongId() {
        return longCounter.incrementAndGet();
    }
}
