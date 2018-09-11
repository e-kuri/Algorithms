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

    interface InputFileProcessor{
        void processLine(String line);
    }

    private static void processInputFile(String path, InputFileProcessor ifp){
        try(FileInputStream fis = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis))){

            String strLine;
            while ((strLine = br.readLine()) != null)   {
                ifp.processLine(strLine);
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
        processInputFile(selectorsFile, line -> selectorsMap.put(line, new MatchingOption(new Person(line)) ));
        processInputFile(selectedFile, line -> selectedMap.put(line, new MatchingOption(new Person(line)) ));
        processInputFile(selectorPreferencesFile, line -> {
            String[] content = line.split("\\s*,\\s*");
            List<MatchingOption> preferences = new LinkedList<>();
            for(int i=1; i<content.length; i++){
                preferences.add(selectedMap.get(content[i]));
            }
            selectorsMap.get(content[0]).setPreferences(preferences);
        });
        processInputFile(selectedPreferencesFile, line -> {
            String[] content = line.split("\\s*,\\s*");
            List<MatchingOption> preferences = new LinkedList<>();
            for(int i=1; i<content.length; i++){
                preferences.add(selectorsMap.get(content[i]));
            }
            selectedMap.get(content[0]).setPreferences(preferences);
        });
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
