package org.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utilities {
    private Utilities(){//nothing at the moment
         }
    public static String loadRescourceContent(String resourceFileName) throws IOException {
        return Files.readString(Path.of("./src/main/resources",resourceFileName ));
    }

/*    public static void main(String[] args) throws IOException {
        System.out.println(loadRescourceContent("vertex.vs"));
    }*/
}
