# Algorithms

This repository contains a set of problems studied at the Computer Science Master at Instituto Politécnico Nacional, Mexico.
The problems included so far are:

# Stable Matching

This algorithms creates a stable match between a group of chooser entities (each of them with an ordered list of preferences) and a group of chosen entities.
The algorithm is composed of two groups: selectors (entities that will start the algorithm by chosing the best possible match from their preferences ) and selected (Entities that will wait to be picked, and will pick the best of the entities who tried to match them).
To run the algorithm, you must update the input files located in the TestCases directory. This directory contains four txt files, and all of them are required to run the algorithm. Its structure is the following:

* selected.txt:
  This file holds the list of selected entities (entities that will be picked by the selectors). This file should contain a list of strings separated by line breaks. Each one of these strings will identify a selected entity, so they should be unique.
  
* selector.txt
  This file holds the list of selector entities (entities that will start proposing to the selected entities that are ranked higher in their preferences list). This file should contain a list of strings separated by line breaks. Each one of these strings will identify a selector entity, so they should be unique.
  
* selected_preferences.txt
  This file holds the ordered list of preferences for each selected entity. Each line on this file is composed by a list of element identifiers, separated by commas, in the following order:
  - The first element is the identifier of the "selected" entity whose preferences will be set.
  - The rest of the list contains the ordered identifier of the preferences that this entity will hold. Each of these identifiers should match a "selector" entity, set in the selectors.txt file.
  
* selectors_preferences.txt
  This file holds the ordered list of preferences for each selector entity. Each line on this file is composed by a list of element identifiers, separated by commas, in the following order:
  - The first element is the identifier of the "selector" entity whose preferences will be set.
  - The rest of the list contains the ordered identifier of the preferences that this entity will hold. Each of these identifiers should match a "selected" entity, set in the selected.txt file.
  
  Once compiled, run it from command line with the collowing instruction:
  java com/kuri/Main <selectors_path> <selected_path> <selectors_preferences_path> <selected_preferences_path>
  
  You can use any input files to test different scenarios, as long as they have the same structure than the included in this example.
