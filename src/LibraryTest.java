import org.junit.Test;

import static org.junit.Assert.*;

public class LibraryTest {

    @Test
    public void testWriteFile() throws Exception {
        Library lib = new Library();
        String name = "Krystian";
        String pass = "testing";
        lib.writeFile(name,pass);
    }
}