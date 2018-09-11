package com.kuri;

import com.kuri.StableMatching.MatchingOption;
import com.kuri.StableMatching.StableMatching;
import com.kuri.exception.NoMorePreferencesException;
import com.kuri.exception.PreferencesNotInitializedException;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.*;
import java.util.*;

public class Main {

    static class Person{
        private String name;

        public Person(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    private static List<Person> getPeopleFromFile(String filePath){
        List<Person> people = new LinkedList<>();
        try(FileInputStream fis = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis))){

            String strLine;

            while ((strLine = br.readLine()) != null)   {
                people.add(new Person(strLine));
            }

        }catch (FileNotFoundException ffe){
            System.out.println("File not found");
        }catch (IOException e){
            System.out.printf("There was an error reading the file");
        }
        return people;
    }

    private static void setPreferencesFromFile(String preferencesPath, Map<String, MatchingOption> preferenceOptions,
                                               Map<String, MatchingOption> choosers){
        try(FileInputStream fis = new FileInputStream(preferencesPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis))){

            String strLine;
            while ((strLine = br.readLine()) != null)   {
               String[] content = strLine.split("\\s*,\\s*");
               List<MatchingOption> preferences = new LinkedList<>();
               for(int i=1; i<content.length; i++){
                    preferences.add(preferenceOptions.get(content[i]));
               }
               choosers.get(content[0]).setPreferences(preferences);
            }

        }catch (FileNotFoundException ffe){
            System.out.println("File not found");
        }catch (IOException e){
            System.out.printf("There was an error reading the file");
        }
    }

    private static List<MatchingOption> getOptionsFromFiles(String selectorsFile,
                                                            String selectedFile,
                                                            String selectorPreferencesFile,
                                                            String selectedPreferencesFile){
        Map<String, MatchingOption> selectorsMap = new HashMap<>();
        Map<String, MatchingOption> selectedMap = new HashMap<>();
        for(Person p : getPeopleFromFile(selectorsFile)){
            selectorsMap.put(p.getName(), new MatchingOption(p));
        }
        for(Person p : getPeopleFromFile(selectedFile)){
            selectedMap.put(p.getName(), new MatchingOption(p));
        }
        setPreferencesFromFile(selectedPreferencesFile, selectorsMap, selectedMap);
        setPreferencesFromFile(selectorPreferencesFile, selectedMap, selectorsMap);

        return new LinkedList<>(selectorsMap.values());
    }

    public static void main(String[] args) {
        List<MatchingOption> selectors = getOptionsFromFiles(args[0], args[1], args[2], args[3]);
        try {
            StableMatching.match(selectors);
        } catch (NoMorePreferencesException e) {
            e.printStackTrace();
        } catch (PreferencesNotInitializedException e) {
            e.printStackTrace();
        }
        printMatches(selectors);
    }

    private static void setPreferences(MatchingOption option, MatchingOption ...preferences){
        option.setPreferences(new LinkedList<>(Arrays.asList(preferences)));
    }

    private static void printMatches(List<MatchingOption> selector){
        ListIterator<MatchingOption> iterator = selector.listIterator();
        while(iterator.hasNext()){
            MatchingOption current = iterator.next();
            StringBuilder printable = new StringBuilder();
            printable.append(current.getCandidate().toString()).append(", ")
                    .append(current.getCurrentMatch().getCandidate().toString());
            System.out.println(printable);
        }
    }
}
