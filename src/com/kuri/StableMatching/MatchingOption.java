package com.kuri.StableMatching;

import com.kuri.exception.NoMorePreferencesException;
import com.kuri.exception.PreferencesNotInitializedException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MatchingOption<T> {
    private T candidate;
    private List<MatchingOption> preferences;
    private MatchingOption currentMatch;
    ListIterator<MatchingOption> preferenceItrator;

    public MatchingOption(T candidate, List<MatchingOption> preferences){
        this.candidate = candidate;
        this.preferences = preferences;
        this.preferenceItrator = preferences.listIterator();
    }

    public MatchingOption(T candidate){
        this.candidate = candidate;
    }

    public void setPreferences(List<MatchingOption> preferences){
        this.preferences = preferences;
        this.preferenceItrator = preferences.listIterator();
    }

    public boolean hasNextPreference() throws PreferencesNotInitializedException {
        if(preferenceItrator == null){
            throw new PreferencesNotInitializedException();
        }
        return preferenceItrator.hasNext();
    }

    public MatchingOption getNextPreference() throws NoMorePreferencesException, PreferencesNotInitializedException {
        if(preferenceItrator == null){
            throw new PreferencesNotInitializedException();
        }
        if(preferenceItrator.hasNext()){
            return preferenceItrator.next();
        }else{
            throw new NoMorePreferencesException();
        }
    }

    public boolean isBetterMatch(MatchingOption candidate) throws PreferencesNotInitializedException {
        if(preferenceItrator == null){
            throw new PreferencesNotInitializedException();
        }
        if(!hasMatch()){
            return true;
        }
        Iterator<MatchingOption> it = preferences.iterator();
        MatchingOption current;
        while(it.hasNext()){
            current = it.next();
            if(current == candidate){
                return true;
            }else if(current == currentMatch){
                return false;
            }
        }
        return false;
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
