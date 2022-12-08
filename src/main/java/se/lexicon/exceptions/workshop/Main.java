package se.lexicon.exceptions.workshop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Customexception.Exceptions;
import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class Main {

    public static void main(String[] args) {
// do not touch
        List<String> maleFirstNames = CSVReader_Writer.getMaleFirstNames();
        List<String> femaleFirstNames = CSVReader_Writer.getFemaleFirstNames();
        List<String> lastNames = null;
        try {
            lastNames = CSVReader_Writer.getLastNames();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // get random person
        System.out.println("______");
        NameService nameService = new NameService(maleFirstNames, femaleFirstNames, lastNames);
        Person test = nameService.getNewRandomPerson();
        System.out.println(test);
        System.out.println("______");
        try {
            nameService.addLastName("test");  // result added and saved in File
        } catch (Exceptions e) {
            System.out.println(e.getMessage());
        }
        try {
            nameService.addFemaleFirstName("Alice"); // exist in system (will call a custom exception)
        } catch (Exceptions e) {
            System.out.println(e.getMessage());
        }
        try {
            nameService.addMaleFirstName("William"); // exist in system (will call a custom exception)
        } catch (Exceptions e) {
            System.out.println(e.getMessage());
        }
    }

}
