package com.kuri.StableMatching;

import com.kuri.exception.NoMorePreferencesException;

import java.util.LinkedList;
import java.util.List;

public class StableMatching {

    public static void match(List<MatchingOption> selectors)throws NoMorePreferencesException{
        List<MatchingOption> unmatchedSelectors = new LinkedList<>(selectors);
        while (unmatchedSelectors.size() > 0){
            MatchingOption selector = unmatchedSelectors.remove(0);
            if(selector.hasNextPreference()){
                tryMatch(selector, selector.getNextPreference(), unmatchedSelectors);
            }
        }
    }

    private static void tryMatch(MatchingOption selector, MatchingOption selected, List<MatchingOption> unmatched){
        if(!selected.isBestMatch(selector)) {
            unmatched.add(0, selector);
        }else {
            selector.match(selected);
            if(selected.hasMatch()) {
                MatchingOption prevMatch = selected.getCurrentMatch();
                prevMatch.unmatch();
                unmatched.add(0, prevMatch);
            }
            selected.match(selector);
        }
    }

}
