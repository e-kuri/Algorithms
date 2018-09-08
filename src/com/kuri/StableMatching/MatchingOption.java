package com.kuri.StableMatching;

import com.kuri.exception.NoMorePreferencesException;

import java.util.LinkedList;
import java.util.List;

public class MatchingOption<T> {
    private T candidate;
    private List<MatchingOption> preferences;
    private int currentPreferenceIndex;
    private MatchingOption currentMatch;

    public MatchingOption(T candidate, List<MatchingOption> preferences){
        this.candidate = candidate;
        this.preferences = preferences;
        this.currentPreferenceIndex = 0;
    }

    public MatchingOption(T candidate){
        this(candidate, new LinkedList<MatchingOption>());
    }

    public void addPreference(MatchingOption preference){
        this.preferences.add(preference);
    }

    public void addPreference(MatchingOption preference, int possition){
        if(possition <= this.preferences.size()){
            this.preferences.add(possition, preference);
        }else{
            addPreference(preference);
        }
    }

    public boolean hasNextPreference(){
        return currentPreferenceIndex < preferences.size();
    }

    public MatchingOption getNextPreference() throws NoMorePreferencesException {
        if(currentPreferenceIndex < preferences.size()){
            return preferences.get(currentPreferenceIndex++);
        }else{
            throw new NoMorePreferencesException();
        }
    }

    public boolean isBestMatch(MatchingOption candidate){
        if(!hasMatch()){
            return true;
        }
        int index = 0;
        while(index < preferences.size() && preferences.get(index) != currentMatch && preferences.get(index) != candidate ){
            index++;
        }
        return preferences.get(index) == candidate;
    }

    public void unmatch(){
        this.currentMatch = null;
    }

    public void match(MatchingOption match){
        this.currentMatch = match;
    }

    public boolean hasMatch(){
        return currentMatch != null;
    }

    public MatchingOption getCurrentMatch() {
        return currentMatch;
    }

    public T getCandidate(){
        return candidate;
    }


}
