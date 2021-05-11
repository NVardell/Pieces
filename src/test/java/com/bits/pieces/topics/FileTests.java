package com.bits.pieces.topics;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/24/2019
 */
public class FileTests {

    private static final String DIR = "C:/Users/NV/Desktop/Data";
    private static final String FILE = "fileChannelTest.txt";


    /**
     * Write with FileChannel
     *      If you are dealing with large files, FileChannel can be faster than standard IO.
     *      The following code write String to a file using FileChannel:
     * @throws IOException
     */
    @Test
    public void givenWritingToFile_whenUsingFileChannel_thenCorrect() throws IOException {
        RandomAccessFile stream = new RandomAccessFile(FILE, "rw");
        FileChannel channel = stream.getChannel();
//        String value = "Hello";

        String value;
        List<List<Integer>> listOfCombos = Arrays.asList(
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000007),
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000020),
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000021),
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000022),
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000023),
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000024),
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000025),
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000026),
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000027),
                Arrays.asList(26000000, 26000001, 26000002, 26000003, 26000004, 26000005, 26000006, 26000028)
        );

        value = listOfCombos.toString();
//        value = Arrays.toString(new List[]{listOfCombos});

        byte[] strBytes = value.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
        buffer.put(strBytes);
        buffer.flip();
        channel.write(buffer);
        stream.close();
        channel.close();

        // verify
        RandomAccessFile reader = new RandomAccessFile(FILE, "r");
        assertThat(reader.readLine(), is(value));
        reader.close();
    }































    @Test
    public void listDesktopContents() {
        Set<String> contents = listFilesUsingJavaIO(DIR);
        assertThat(contents, notNullValue());
        contents.forEach(System.out::println);
    }

    private Set<String> listFilesUsingJavaIO(String dir) {
        System.out.println("Looking for files in - " + dir);
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }



    @Test
    public void testFileMethods() {
        String fName = "config/days.txt";

        // Method #1 - Read all lines as a Stream
        fileStreamUsingFiles(fName);

        System.out.println();

        // Method #2 - Read file with a filter
        filterFileData(fName);

        System.out.println();

        // Method #3 - In Java8, 'BufferedReader' has the 'lines()' method which returns the file content as a Stream
        fileStreamUsingBufferedReader(fName);
    }

    // Method #1
    private static void fileStreamUsingFiles(String fileName) {
        try {
            Stream<String> lines = Files.lines(Paths.get(fileName));
            System.out.println("<!-----Read all lines as a Stream-----!>");
            lines.forEach(System.out :: println);
            lines.close();
        } catch(IOException io) {
            io.printStackTrace();
        }
    }

    // Method #2
    private static void filterFileData(String fileName) {
        try {
            Stream<String> lines = Files.lines(Paths.get(fileName)).filter(line -> line.startsWith("s"));
            System.out.println("<!-----Filtering the file data using Java8 filtering-----!>");
            lines.forEach(System.out :: println);
            lines.close();
        } catch(IOException io) {
            io.printStackTrace();
        }
    }

    // Method #3
    private static void fileStreamUsingBufferedReader(String fileName) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
            Stream <String> lines = br.lines().map(str -> str.toUpperCase());
            System.out.println("<!-----Read all lines by using BufferedReader-----!>");
            lines.forEach(System.out::println);
            lines.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


    private static void fileWalk_ListAllFiles() {
        try (Stream<Path> walk = Files.walk(Paths.get(DIR))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void fileWalk_ListAllFolders() {
        try (Stream<Path> walk = Files.walk(Paths.get(DIR))) {

            List<String> result = walk.filter(Files::isDirectory)
                    .map(x -> x.toString()).collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void fileWalk_ListAllFilesThatEndInTxt() {
        try (Stream<Path> walk = Files.walk(Paths.get(DIR))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".txt")).collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void fileWalk_findSpecificFileByName() {
        try (Stream<Path> walk = Files.walk(Paths.get(DIR))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.contains("HeaderAnalyzer.java"))
                    .collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void fileRead_withStream() {
        String fileName = "c://lines.txt";

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileRead_withStreamFilters() {
        String fileName = "c://lines.txt";
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            //1. filter line 3
            //2. convert all content to upper case
            //3. convert it into a List
            list = stream
                    .filter(line -> !line.startsWith("line3"))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);
    }

    private static void fileRead_withBufferStream() {
        String fileName = "c://lines.txt";
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);
    }

    private static void fileRead_withJava8() throws IOException {
        //@Since 1.8

        Stream<String> lines = Files.lines(Paths.get("app.log"));

        List<String> content = lines.collect(Collectors.toList());

    }
    private static void fileRead_toListOfStrings() {
        try {

            Stream<String> lines = Files.lines(Paths.get("app.log"));
            List<String> content = lines.collect(Collectors.toList());
            content.forEach(x -> System.out.println(x));

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    private static List readByJava8(String fileName) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.collect(Collectors.toList());
        }
        return result;
    }
    private static void fileRead_with5() {
        String path = "c:\\projects\\app.log";

        try (Stream<String> lines = Files.lines(Paths.get(path))) {

            // Formatting like \r\n will be lost
            // String content = lines.collect(Collectors.joining());

            // UNIX \n, WIndows \r\n
            String content = lines.collect(Collectors.joining(System.lineSeparator()));
            System.out.println(content);

            // File to List
            //List<String> list = lines.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void fileRead_withApacheCommonsIO() {
//        <dependency>
//            <groupId>commons-io</groupId>
//            <artifactId>commons-io</artifactId>
//            <version>2.6</version>
//        </dependency>
        String path = "c:\\projects\\app.log";

        try {
            String content = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * If we are reading a large file, FileChannel can be faster than standard IO.
     * @throws IOException
     */
    @Test
    public void whenReadWithFileChannel_thenCorrect()
            throws IOException {
        String expected_value = "Hello world";
        String file = "src/test/resources/test_read.txt";
        RandomAccessFile reader = new RandomAccessFile(file, "r");
        FileChannel channel = reader.getChannel();

        int bufferSize = 1024;
        if (bufferSize > channel.size()) {
            bufferSize = (int) channel.size();
        }
        ByteBuffer buff = ByteBuffer.allocate(bufferSize);
        channel.read(buff);
        buff.flip();

        assertThat(new String(buff.array()), is(expected_value));
        channel.close();
        reader.close();
    }


    @Test
    public void whenReadWithDataInputStream_thenCorrect() throws IOException {
        String expectedValue = "Hello";
        String file ="src/test/resources/test_read.txt";
        DataInputStream reader = new DataInputStream(new FileInputStream(file));
        String result = reader.readUTF();
        reader.close();

        assertThat(result, is(expectedValue));
    }


    @Test
    public void givenFileName_whenUsingIOUtils_thenFileData() throws IOException {
        String expectedData = "This is a content of the file";

        FileInputStream fis = new FileInputStream("src/test/resources/fileToRead.txt");
        String data = IOUtils.toString(fis, "UTF-8");

        assertThat(data.trim(), is(expectedData));
    }

    @Test
    public void givenFilePath_whenUsingFilesLines_thenFileData() throws IOException, URISyntaxException {
        String expectedData = "Hello World from fileTest.txt!!!";

        Path path = Paths.get(getClass().getClassLoader()
                .getResource("fileTest.txt").toURI());

        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();

        assertThat(data.trim(), is(expectedData));
    }









    public static void main(String args[]) {

        String crunchifyFile = "/Users/<username>/Downloads/crunchify-java8-stream.txt";

        // lines() and Stream Approach
        CrunchifyReadFile1(crunchifyFile);

        // newBufferedReader and Stream Approach
        CrunchifyReadFile2(crunchifyFile);
    }

    // Read file using lines() and Stream Approach
    private static void CrunchifyReadFile1(String crunchifyFile) {

        Stream<String> crunchifyStream = null;
        try {

            // Read all lines from a file as a Stream. Bytes from the file are decoded into characters using the UTF-8 charset
            crunchifyStream = Files.lines(Paths.get(crunchifyFile));

        } catch (IOException e) {
            e.printStackTrace();
        }

        log("============= Result from lines() and Stream Approach =============");
        crunchifyStream.forEach(System.out::println);
    }

    // Read file using newBufferedReader and Stream Approach
    private static void CrunchifyReadFile2(String crunchifyFile) {
        List<String> crunchifyList = new ArrayList<>();

        BufferedReader crunchifyBufferReader = null;
        try {

            // newBufferedReader opens a file for reading
            crunchifyBufferReader = Files.newBufferedReader(Paths.get(crunchifyFile));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // toList: returns a Collector that accumulates the input elements into a new List
        // lines(): returns a Stream, the elements of which are lines read from this BufferedReader
        crunchifyList = crunchifyBufferReader.lines().collect(Collectors.toList());

        log("\n============= Result from newBufferedReader and Stream Approach =============");

        // forEach: performs the given action for each element of the Iterable until all elements have been processed or the
        // action throws an exception.
        crunchifyList.forEach(System.out::println);

    }

    private static void log(String string) {
        System.out.println(string);

    }
}
