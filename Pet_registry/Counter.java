package Pet_registry;

import java.io.IOException;

public class Counter implements AutoCloseable {
    private Integer counter = 0;

    public void add() {
        ++counter;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void close() throws IOException {
        throw new IOException();
    }
}
