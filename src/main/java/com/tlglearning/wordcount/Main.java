package com.tlglearning.wordcount;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

  private static final String TEST_FILE_NAME = "hound-of-the-baskervilles.txt";

  public static void main(String[] args) throws URISyntaxException, IOException {
    // TODO: use a class loader that looks at the class the
    // URL - uniform resource locator, locates resources in a network (every URL is a URI, but not the opposite is true)
    // URI - uniform resource identifier, identifies our resource
    URI uri = Main.class
        .getClassLoader()
        .getResource(TEST_FILE_NAME)
        .toURI();

    Path path = Paths.get(uri);
    String text = Files.readString(path);
    WordCounter counter = new WordCounter(text);
    // TODO Do something with counter.
    System.out.println(counter);
  }
}
