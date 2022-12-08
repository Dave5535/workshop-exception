package se.lexicon.exceptions.workshop.fileIO;

import Customexception.Exceptions;
import se.lexicon.exceptions.workshop.data_access.NameService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader_Writer {
    /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch FileNotFoundException and IOException
     * You should also close the Buffered reader in the finally block
     *
     * @return List<String>of male firstnames
     */
    public static List<String> getMaleFirstNames() {

        BufferedReader reader = null;
        List<String> names = null;

        try {
            reader = Files.newBufferedReader(Paths.get("firstname_males.txt"));
            names = reader.lines().flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e + " was null");
            }
        }
        return names;
    }


    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     *
     * @return
     */
    public static List<String> getFemaleFirstNames() {

        List<String> names = null;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("firstname_female.txt"))

        ) {
            names = reader.lines().flatMap(line -> Stream.of(line.split(","))).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e);
        }


        return names;
    }


    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     *
     * @return List <String> of last names
     * @throws IOException
     */
    public static List<String> getLastNames() throws IOException {

        List<String> names = null;
        BufferedReader reader = null;

        try {
            reader = Files.newBufferedReader(Paths.get("lastnames.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());


        } finally {
            if (reader != null) {
                reader.close();
            } else {
                System.out.println("lastname was not found");

            }
        }
        return names;
    }


    public static void saveLastNames(List<String> lastNames) throws Exceptions {

        try {
            List<String> strings = Files.readAllLines(Paths.get("lastnames.txt"));
            {
                for (int i = 0; i < lastNames.size(); i++)
                    if (strings.contains(lastNames.get(i)))
                        lastNames.remove(lastNames.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("lastnames.txt"));
            for (String toWrite : lastNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveFemaleNames(List<String> femaleNames) throws Exceptions {

        try {
            List<String> strings = Files.readAllLines(Paths.get("firstname_female.txt"));
            {
                for (int i = 0; i < femaleNames.size(); i++)
                    if (strings.contains(femaleNames.get(i)))
                        femaleNames.remove(femaleNames.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"));
            for (String toWrite : femaleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void saveMaleNames(List<String> maleNames) throws Exceptions {

        try {
            List<String> strings = Files.readAllLines(Paths.get("firstname_males.txt"));
            {
                for (int i = 0; i < maleNames.size(); i++)
                    if (strings.contains(maleNames.get(i)))
                        maleNames.remove(maleNames.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"));
            for (String toWrite : maleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
