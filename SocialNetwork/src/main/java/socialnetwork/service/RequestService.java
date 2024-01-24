package socialnetwork.service;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Request;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.repository.Repository;

public class RequestService {
    private Repository<Tuple<Long, Long>, Request> repoRequest;
    private Repository<Long, Utilizator> repoUtilizatori;
    private Repository<Tuple<Long,Long>, Prietenie> repoPrietenie;

    public RequestService(Repository<Tuple<Long, Long>,Request> repo1,Repository<Long,Utilizator> repo2,Repository<Tuple<Long,Long>,Prietenie> repo3)
    {
        this.repoRequest = repo1;
        this.repoUtilizatori = repo2;
        this.repoPrietenie = repo3;
    }

    public Request getOne(Tuple<Long,Long> id){return repoRequest.findOne(id);}
    public Iterable<Request> getAll(){
        return repoRequest.findAll();
    }

    public void send_request(Long id1,Long id2)
    {
        Tuple tup = new Tuple(id1,id2);

        Utilizator u1 = repoUtilizatori.findOne(id1);
        Utilizator u2 = repoUtilizatori.findOne(id2);

        if(u1==null)
            throw new ValidationException("no user with id1...");
        if(u2==null )
            throw new ValidationException("no user with id2...");

        if(u1==u2)
            throw new ValidationException("can not make friends with himself");
        if(repoPrietenie.findOne(new Tuple(id1,id2))!=null)
            throw new ValidationException("this friendship already exists: \n" +repoPrietenie.findOne(new Tuple(id1,id2)));
        if(repoPrietenie.findOne(new Tuple(id2,id1))!=null)
            throw new ValidationException("this friendship already exists: \n" +repoPrietenie.findOne(new Tuple(id2,id1)));
//        System.out.println(repoPrietenie.findOne(new Tuple(id1,id2)));
//        System.out.println(repoPrietenie.findOne(new Tuple(id2,id1)));

        if(repoRequest.findOne(new Tuple(id1,id2)) !=null)
            throw new ValidationException("request was send a long time ago...");



        Request req = new Request(id1,id2);
        repoRequest.save(req);


//        u1.addFriend(u2);
//        u2.addFriend(u1);
//        Prietenie p = new Prietenie();
//        p.setId(tup);
//        repoPrietenie.save(p);
//        repoPrietenie.findAll().forEach(System.out::println);
    }


    public void accept_friend_request(Long id1,Long id2)
    {
        Tuple tup = new Tuple(id1,id2);

        Utilizator u1 = repoUtilizatori.findOne(id1);
        Utilizator u2 = repoUtilizatori.findOne(id2);

        if(u1==null)
            throw new ValidationException("no user with id1...");
        if(u2==null )
            throw new ValidationException("no user with id2...");

        if(u1==u2)
            throw new ValidationException("can not make friends with himself");
        if(repoPrietenie.findOne(new Tuple(id1,id2))!=null)
            throw new ValidationException("this friendship already exists: \n" +repoPrietenie.findOne(new Tuple(id1,id2)));
        if(repoPrietenie.findOne(new Tuple(id2,id1))!=null)
            throw new ValidationException("this friendship already exists: \n" +repoPrietenie.findOne(new Tuple(id2,id1)));
//        System.out.println(repoPrietenie.findOne(new Tuple(id1,id2)));
//        System.out.println(repoPrietenie.findOne(new Tuple(id2,id1)));

        if(repoRequest.findOne(new Tuple(id1,id2)) ==null)
            throw new ValidationException("no friend request to accept");

         Request req = repoRequest.findOne( new Tuple(id1,id2));
         req.setStatus("accepted");
         repoRequest.update(req);

         u1.addFriend(u2);
        u2.addFriend(u1);
        Prietenie p = new Prietenie();
        p.setId(tup);
        repoPrietenie.save(p);
        repoPrietenie.findAll().forEach(System.out::println);


    }

    public void decline_friend_request(Long id1,Long id2)
    {
        Tuple tup = new Tuple(id1,id2);

        Utilizator u1 = repoUtilizatori.findOne(id1);
        Utilizator u2 = repoUtilizatori.findOne(id2);

        if(u1==null)
            throw new ValidationException("no user with id1...");
        if(u2==null )
            throw new ValidationException("no user with id2...");

        if(u1==u2)
            throw new ValidationException("can not make friends with himself");


        if(repoRequest.findOne(new Tuple(id2,id1)) ==null)
            throw new ValidationException("no friend request to decline");

        Request req = repoRequest.findOne( new Tuple(id2,id1));
        req.setStatus("declined");
        repoRequest.update(req);

        if(repoPrietenie.findOne(new Tuple(id1,id2))  != null)
            repoPrietenie.delete(new Tuple(id1,id2));


    }


}