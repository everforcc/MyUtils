package cc.java8.onjava8.streams;

// streams/RandomWords.java
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.io.*;
import java.nio.file.*;
public class RandomWords implements Supplier<String> {
    List<String> words = new ArrayList<>();
    Random rand = new Random(47);
    RandomWords(String fname) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fname));
        // 略过第一行
        for (String line : lines.subList(1, lines.size())) {

            // 这个正则随后再说，不是这次的关键
            for (String word : line.split("[ .?,]+"))
                words.add(word.toLowerCase());
        }
    }
    public String get() {
        return words.get(rand.nextInt(words.size()));
    }
    @Override
    public String toString() {
        return words.stream()
                .collect(Collectors.joining(" "));
    }
    public static void main(String[] args) throws Exception {
        System.out.println(
                Stream.generate(new RandomWords("D:\\environment\\code\\idea\\github\\MyUtils\\src\\main\\java\\cn\\cc\\onjava8\\streams\\Cheese.dat"))
                        .limit(10)
                        .collect(Collectors.joining(" ")));
    }
}

