package socialnetwork.domain;

import socialnetwork.domain.validators.ValidationException;
import sun.nio.ch.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utilizator extends Entity<Long>{
    private String firstName;
    private String lastName;
    private List<Utilizator> friends;
    private String username;
    private String password;
    private String email;

    public Utilizator()
    {
        friends = new ArrayList<Utilizator>();
    };


    public Utilizator(String firstName, String lastName,String username,String password,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        friends = new ArrayList<Utilizator>();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Utilizator(String firstName,String lasName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        friends = new ArrayList<Utilizator>();
    }


//    public Utilizator(String firstName,String lastName,int id)
//    {
//
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getusername(){return this.username;}
    public String getpassword(){return this.password;}
    public String getEmail(){return this.email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Utilizator> getFriends() {
        return friends;
    }

    public void addFriend(Utilizator fr)
    { int ok=0;
        System.out.println("cc");
//        System.out.println(friends);
            for(Utilizator user: friends)
                if (user.equals(fr)) {
                    ok = 1;
                }

        if(ok==0)
        {
            friends.add(fr);
        }
    }
    public void remove_Friend(Utilizator fr)
    {
//        int ok=0;
//        System.out.println(friends);
//        for(Utilizator user:friends)
//            if(user.equals(fr))
//            {
//                ok=1;
//            }
//        if(ok==0)
//            throw new ValidationException("utilizatorul nu este in lista de prieteni");
//        else
//            friends.remove(fr);
    }

    @Override
    public String toString() {
        String str="[ ";
        if(friends.size() !=0)
        for(Utilizator i:friends)
            str=str+i.getId() + " ";
        str=str+"]";
        return "Utilizator{" +
                "Id=" + this.getId() + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", friends=" + str +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizator)) return false;
        Utilizator that = (Utilizator) o;
        return getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                getFriends().equals(that.getFriends());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getFriends());
    }
}