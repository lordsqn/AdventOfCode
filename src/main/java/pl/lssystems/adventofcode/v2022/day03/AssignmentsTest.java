package pl.lssystems.adventofcode.v2022.day03;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AssignmentsTest {
    String buffer = "";

    public String findCommonLetter(String first, String second) {
        return first.chars().filter(a -> second.chars().anyMatch(b -> a == b)).mapToObj(a -> ((char)a)+"").collect(Collectors.joining());
    }

    @Test @TaskInput
    public void assignment1() {
        AtomicInteger sum = new AtomicInteger();

        Input.processLines(line -> {
            char commonChar = findCommonLetter(line.substring(0, line.length()/2), line.substring(line.length()/2)).charAt(0);
            sum.addAndGet(Character.isUpperCase(commonChar) ? commonChar - 38 : commonChar - 96);
        });

        System.out.println("Priority count: " + sum);
    }

    @Test @TaskInput
    public void assignment2() {
        AtomicInteger sum = new AtomicInteger();
        AtomicBoolean sumGroup = new AtomicBoolean(false);

        Input.processLines(line -> {
            if (buffer.isEmpty()) {
                buffer = line;
                return;
            }

            buffer = findCommonLetter(buffer, line) + "";

            if (sumGroup.getAndSet(true)) {
                sum.addAndGet(Character.isUpperCase(buffer.charAt(0)) ? buffer.charAt(0) - 38 : buffer.charAt(0) - 96);
                buffer = "";
                sumGroup.set(false);
            }
        });

        System.out.println("Group priority count: " + sum);
    }

}
