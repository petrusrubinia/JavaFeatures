package com.company.streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WaysOFCreatingStreams {


    public WaysOFCreatingStreams() {
    }

    /**
     * Create strema using array
     */
    public Stream<String> createStreamUsingArray() {
        String[] array = new String[]{"a", "b", "c"};
        return Arrays.stream(array);
    }

    public Stream<String> createStreamUsingList(){
        List<String> list = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};
        return list.stream();
    }

    /**
     * When builder is used, the desired type should be additionally specified in the
     * right part of the statement otherwise the build() method will create an instance of the
     * Stream<Object>
     * @return
     */
    public Stream<String> createStreamUsingBuilder(){
        return Stream.<String>builder().add("a").add("b").add("c").build();
    }

    /**
     * the generate() method accepts a Supplier<T> for element generation
     * The developer should specify the desired size, otherwise the method will work until
     * it reached the memory limit
     * @return
     */
    public Stream<String> createStreamUsingGenerate() {
        return Stream.generate(() -> "element").limit(10);
    }

    /**
     * Generate a Stream<String> of a text file through the lines() method.
     * Every line of the text becomes an element of the stream.
     * @return
     * @throws IOException
     */
    public Stream<String> createStreamByReadingFile() throws IOException {
        Path path = Paths.get("C:\\file.txt");
        Stream<String> streamOfStrings = Files.lines(path);
        Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
        return streamOfStrings;
    }


    public ArrayList<String> createList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("One");
        list.add("OneAndOnly");
        list.add("Derek");
        list.add("Change");
        list.add("factory");
        list.add("justBefore");
        list.add("Italy");
        list.add("Italy");
        list.add("Thursday");
        list.add("");
        list.add("");

        return list;
    }
}
