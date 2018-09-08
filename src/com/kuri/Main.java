package com.kuri;

import com.kuri.StableMatching.MatchingOption;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static class Person{
        private String name;

        public Person(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static List<Person> getPeopleFromFile(String filePath){
        List<Person> people = new LinkedList<>();
        try(FileInputStream fis = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis))){

            String strLine;

            while ((strLine = br.readLine()) != null)   {
                // Print the content on the console
                people.add(new Person(strLine));
            }

        }catch (FileNotFoundException ffe){
            System.out.println("File not found");
        }catch (IOException e){
            System.out.printf("There was an error reading the file");
        }
        return people;
    }

    public static void main(String[] args) {
        Person x = new Person("Xavier");
        Person y = new Person("Yancey");
        Person z = new Person("Zeus");

        Person a = new Person("Amy");
        Person b = new Person("Bertha");
        Person c = new Person("Claire");

        List<MatchingOption> selectors = new ArrayList<>(3);
        List<MatchingOption> selected = new ArrayList<>(3);

        selectors.add(new MatchingOption(x,
            new ArrayList<Person>(){{
                add(a);
                add(b);
                add(c);
            }}
        ));

    }
}
