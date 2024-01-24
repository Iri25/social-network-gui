# social-network-fx

Java application with a 4-layered architecture: data access layer ([domain package]()), persistence layer ([repository package]()), business layer ([service package]()) and presentation layer ([userinterface package]()). The data are saved in csv files ([data package]()). The strategy model was used to validate the data and classes were defined to treat the exepects ([validators package]()). Key concepts are encapsulation, inheritance, polymorphism, validations, exceptions, reading from files and storing in files. The user interface is console type.

Application for a social network with JavaFX graphical interface which supports operations such as:

Add user
Remove user
Add friend
Revove friend
Display number of communities (the number of connected components in the network graph)
Display the most sociable community (the connected component in the network with the longest path)
Display all friendships of a user read from the keyboard
Display all the friendships of a user, created in a certain month of the year, the user and the month of the year are read from the keyboard
Display (chronologically) the conversations of two users, read from the keyboard
Simulate sending a friendship invitation and adding the relationship only if the invited user accepts it. The status of the requests is pending, approved or rejected.
