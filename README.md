# social-network-gui

Java application with a 5-layered architecture: 
- data access layer ([domain package](https://github.com/Iri25/social-network-fx/tree/main/SocialNetwork/src/main/java/socialnetwork/domain)
- persistence layer ([repository package](https://github.com/Iri25/social-network-fx/tree/main/SocialNetwork/src/main/java/socialnetwork/repository))
- business layer ([service package](https://github.com/Iri25/social-network-fx/tree/main/SocialNetwork/src/main/java/socialnetwork/service))
- presentation layer ([user interface package](https://github.com/Iri25/social-network-fx/tree/main/SocialNetwork/src/main/java/socialnetwork/ui))
- application layer ([controller package](https://github.com/Iri25/social-network-gui/tree/main/SocialNetwork/src/main/java/controller)).

The data is saved in the postgres database using the connection configurations from the [config.properties file](https://github.com/Iri25/social-network-fx/blob/main/SocialNetwork/src/main/resources/config.properties). The data can also be saved in csv files ([data package](https://github.com/Iri25/social-network-fx/tree/main/SocialNetwork/data)), but the repository type must be changed. The strategy model was used to validate the data and classes were defined to treat the exepects ([validators package](https://github.com/Iri25/social-network-fx/tree/main/SocialNetwork/src/main/java/socialnetwork/domain/validators)). The interaction with the user is done through a graphical interface (GUI), developed in JavaFX (fxml files are found in [views package](https://github.com/Iri25/social-network-gui/tree/main/SocialNetwork/src/main/resources/Views)).

## Key concepts:
- abstraction
- encapsulation
- inheritance
- polymorphism
- validations
- exceptions
- reading from files
- storing in files,
- reading from a database
- storing from a database

## Requirements:
Desktop application for a social network with a graphical interface which supports operations such as:

1. Add user
2. Remove user
3. Add friend
4. Revove friend
5. Display number of communities (the number of connected components in the network graph)
6. Display the most sociable community (the connected component in the network with the longest path)
7. Display all friendships of a user read from the keyboard
8. Display all the friendships of a user, created in a certain month of the year, the user and the month of the year are read from the keyboard
9. Display (chronologically) the conversations of two users, read from the keyboard
10. Simulate sending a friendship invitation and adding the relationship only if the invited user accepts it. The status of the requests is pending, approved or rejected.
