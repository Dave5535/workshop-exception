package se.lexicon.exceptions.workshop.data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import Customexception.Exceptions;
import se.lexicon.exceptions.workshop.domain.Gender;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class NameService {


    private List<String> maleFirstNames;
    private List<String> femaleFirstNames;
    private List<String> lastNames;
    private static Random random = new Random();

    //should be no nulls
    public NameService(List<String> maleFirstNames, List<String> femaleFirstNames, List<String> lastNames) {

        this.maleFirstNames = maleFirstNames;
        this.femaleFirstNames = femaleFirstNames;
        this.lastNames = lastNames;
    }

    public Person getNewRandomPerson() {
        Gender gender = getRandomGender();
        Person person = null;
        switch (gender) {
            case MALE:
                person = new Person(getRandomMaleFirstName(), getRandomLastName(), gender);
                break;
            case FEMALE:
                person = new Person(getRandomFemaleFirstName(), getRandomLastName(), gender);
                break;
        }
        return person;
    }


    public String getRandomFemaleFirstName() {
        return femaleFirstNames.get(random.nextInt(femaleFirstNames.size()));
    }

    public String getRandomMaleFirstName() {
        return maleFirstNames.get(random.nextInt(maleFirstNames.size()));
    }

    public String getRandomLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    public Gender getRandomGender() {
        return random.nextInt(100) > 50 ? Gender.FEMALE : Gender.MALE;
    }


    /**
     * Here you need to check if List<String> femaleFirstNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param name
     */
    public void addFemaleFirstName(String name) throws Exceptions{

        try {List<String> strings = Files.readAllLines(Paths.get("firstname_female.txt"));
            for (String s : strings)
                if (s.contains(name)){
                    throw new Exceptions("DuplicateNameException");
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        femaleFirstNames.add(name);
        CSVReader_Writer.saveFemaleNames(femaleFirstNames);
        System.out.println("femaleFirstName Added");
    }

    /**
     * Here you need to check if List<String> maleFirstNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param name
     */
    public void addMaleFirstName(String name) throws Exceptions {

        try {List<String> strings = Files.readAllLines(Paths.get("firstname_males.txt"));
            for (String s : strings)
            if (s.contains(name)){
                throw new Exceptions("DuplicateNameException");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
                maleFirstNames.add(name);
                CSVReader_Writer.saveMaleNames(maleFirstNames);
        System.out.println("maleFirstName Added");
    }

    /**
     * Here you need to check if List<String> lastNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param lastName
     */
    public void addLastName(String lastName) throws Exceptions {
        try {
            List<String> strings = Files.readAllLines(Paths.get("lastnames.txt"));
           for (String s : strings)
            if (s.contains(lastName)){
                throw new Exceptions("DuplicateNameException");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            lastNames.add(lastName);
            CSVReader_Writer.saveLastNames(lastNames);
            System.out.println("LastName Added");
    }
}
