package socialnetwork.service;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Request;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.repository.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrietenieService {
    private Repository<Long,Utilizator> repoUtilizatori;
    private Repository<Tuple<Long,Long>,Prietenie> repoPrietenie;
    private Repository<Tuple<Long,Long>, Request> repoRequest;

    public PrietenieService(Repository<Long, Utilizator> repo, Repository<Tuple<Long,Long>, Prietenie>repo2,Repository<Tuple<Long,Long>, Request>repo3 )
    {
        this.repoUtilizatori = repo;
        this.repoPrietenie = repo2;
        this.repoRequest = repo3;
    }

    // cerinta 1
    // cerinta 1

    public ArrayList<Tuple<Long,Long>> get_prietenii_for_user(Long id){
        ArrayList<Tuple<Long,Long>> prieteni = new ArrayList<Tuple<Long,Long>>();

        repoPrietenie.findAll().forEach(x->{
            if(x.getId().getLeft() == id )
                prieteni.add(new Tuple(id,x.getId().getRight()));
            else if(x.getId().getRight()==id)
                prieteni.add(new Tuple(x.getId().getLeft(),id));
        });

        return prieteni;
    }


    public List<Tuple> get_friends_list(Long id)
    {
        ArrayList<Tuple<Utilizator,LocalDateTime>> friends_list = new ArrayList<Tuple<Utilizator,LocalDateTime>>();

        Utilizator user = repoUtilizatori.findOne(id);

        if(user == null)
            throw new IllegalArgumentException("no id for this user");


        //return user.getFriends()

//        return this.get_prietenii_for_user(id).stream().map(x->{
//        if(x.getLeft() == id)
//        {
//            Utilizator friend = repoUtilizatori.findOne(x.getRight()) ;
//            Prietenie prietenie = repoPrietenie.findOne(x);
//
//            return "" + friend.getFirstName() + " | " + friend.getLastName() + " | " + prietenie.getDate();
//
//
//        }
//        else{
//
//            Utilizator friend = repoUtilizatori.findOne(x.getLeft()) ;
//            Prietenie prietenie = repoPrietenie.findOne(x);
//
//            return "" + friend.getFirstName() + " | " + friend.getLastName() + " | " + prietenie.getDate();
//
//        }
//
//        }).collect(Collectors.toList());

        return this.get_prietenii_for_user(id).stream().map(x->{
            if(x.getLeft()==id)
            {
                Utilizator friend = repoUtilizatori.findOne(x.getRight());
                Prietenie prietenie = repoPrietenie.findOne(x);
                return new Tuple(friend,prietenie);
            }
            else{
                Utilizator friend = repoUtilizatori.findOne(x.getLeft());
                Prietenie prietenie = repoPrietenie.findOne(x);

                return new Tuple(friend,prietenie);
            }
        }).collect(Collectors.toList());

    };

        // end cerinta 1

        // START CERINTA 2

    public List<String> get_friends_list_by_month(Long id,String luna)
    {
        ArrayList<Tuple<Utilizator,LocalDateTime>> friends_list = new ArrayList<Tuple<Utilizator,LocalDateTime>>();

        Utilizator user = repoUtilizatori.findOne(id);

        if(user == null)
            throw new IllegalArgumentException("no id for this user");


        //return user.getFriends()

        return this.get_prietenii_for_user(id).stream().filter(x->{
            Prietenie prietenie = repoPrietenie.findOne(x);
            try{
                Month l = Month.valueOf(luna.toUpperCase());
                if(prietenie.getDate().getMonth() == l)
                    return true;

            }catch(Exception e)
            {
                return false;
            }

            return false;

        }).map(x->{
            if(x.getLeft() == id)
            {
                Utilizator friend = repoUtilizatori.findOne(x.getRight()) ;
                Prietenie prietenie = repoPrietenie.findOne(x);

                return "" + friend.getFirstName() + " | " + friend.getLastName() + " | " + prietenie.getDate();
            }
            else{

                Utilizator friend = repoUtilizatori.findOne(x.getLeft()) ;
                Prietenie prietenie = repoPrietenie.findOne(x);

                return "" + friend.getFirstName() + " | " + friend.getLastName() + " | " + prietenie.getDate();

            }

        }).collect(Collectors.toList());
    };

}
