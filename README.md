#### JETS APPLICATION

### Overview
This project is designed to utilize superclasses, interfaces, and ArrayLists
with items being loaded from a text file. The text file is composed of
various types of aircrafts with their respected attributes (speed, range,
price, etc.). Each aircraft is created as an object and stored into an
ArrayList in a class called Airfield, which serves as a repository for
the current inventory. There are 3 different categories of aircrafts -
fighter - trainer - and transport. Each of those three types of planes have
their own class are are all subclasses of an abstract class called Jet.

A class called JetApplication is where the program starts
from the main method. The main calls a launch method where the user is
immediately prompted with a menu of options to select from:

1. List fleet
2. Fly a jet
3. View fastest jet
4. View jet with longest range
5. Load all Cargo Jets
6. Dogfight!
7. Add a jet to Fleet
8. Remove a jet from Fleet
9. Quit


The menu was created with a while-loop switch statement that stops when the user
selects option 9. If 9 is selected, the user is asked if they want to save as a
text file. If yes is selected, the user will be asked to name the file before
the new text file is created. Options 5 & 6 call classes that use implemented
interfaces. Option 7 allows a user to add a jet while option 8 allows a user
to remove one. The users add or remove changes are reflected when the user
chooses the option to display the fleet.  

###  Challenges
The first challenge was loading the ArrayList from the text file. I
unknowingly added two additional blank lines in my text file, which in return,
kept displaying an array-index-out-of-bounds exception.

Another challenge happened when I was trying to remove a plane. I originally
had a counter in the constructor for each plane so that the user could just
type in the counter (inventory) number when removing a plane. The issue was that
I used the remove() method to remove each plane, and so each time a plane was
removed, the number associated with the remaining planes didn't correlate with
the index number, therefore, I kept getting an exception. To solve this issue,
I needed to use a counter that was not in the constructor, but rather in
the displayPlanes method that I used to display all the planes. This way, the
counter matched the remove (int index) number.

### Thoughts
I really enjoyed this project. This week had a lot of new concepts for me to
digest, and this project allowed me to wrap everything together and develop
a better understanding of using abstract methods, overrides, and interfaces.
I also enjoyed the dynamic flexibility of lists over arrays.
