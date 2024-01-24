package socialnetwork.service;

import org.w3c.dom.ls.LSOutput;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Request;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.repository.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

public class UtilizatorService  {
    private Repository<Long, Utilizator> repoUtilizatori;
    private Repository<Tuple<Long,Long>,Prietenie>repoPrietenie;
    private Repository<Tuple<Long,Long>, Request> repoRequest;



    public UtilizatorService(Repository<Long, Utilizator> repo, Repository<Tuple<Long,Long>, Prietenie>repo2 ,Repository<Tuple<Long,Long>,Request> repo3){
        this.repoUtilizatori = repo;
        this.repoPrietenie  = repo2;
        this.repoRequest = repo3;
        add_allfriends();
    }

    public void add_allfriends()
    {
        System.out.println("AICI IN ADD FRIENDS LALL");
        repoPrietenie.findAll().forEach(x->{
            System.out.println(x);
           Utilizator first = repoUtilizatori.findOne(x.getId().getLeft());
           Utilizator second =repoUtilizatori.findOne(x.getId().getRight());
           first.addFriend(second);
           second.addFriend(first);
        });
        repoUtilizatori.findAll().forEach(x->{
            System.out.println(x);
        });
    }



    public Utilizator addUtilizator(Utilizator messageTask) {
        Utilizator task = repoUtilizatori.save(messageTask);
        return task;
    }

    public Iterable<Utilizator> getAll(){
        return repoUtilizatori.findAll();
    }

    public Utilizator getOne(Long id){
        return repoUtilizatori.findOne(id);
    }

    public void delete_user(Long id)
    {
        // ask why it does not work
        ArrayList<Tuple<Long,Long>> ar = new ArrayList<Tuple<Long,Long>>();
        repoPrietenie.findAll().forEach(e->{
            if(e.getId().getLeft()==id || e.getId().getRight()==id)
            {
               // System.out.println(e.getId());
                ar.add(e.getId());
            }
//                delete_friendship(e.getId().getLeft(),e.getId().getRight());
        });

        for(Tuple<Long,Long> e:ar)
        {
            System.out.println(e.getLeft() + " " + e.getRight());
            this.delete_friendship(e.getLeft(),e.getRight());
        }

        repoUtilizatori.findAll().forEach(e-> System.out.println(e));
        repoUtilizatori.delete(id);

        Iterable<Request> req =  repoRequest.findAll();
        for(Request r:req)
        {
            System.out.println(r);
            if(r.getId().getLeft() == id)
                repoRequest.delete(r.getId());
            else if(r.getId().getRight() == id)
                repoRequest.delete(r.getId());
        }

//        try {
//            Predicate<Prietenie> verify = x -> x.getId().getRight() == id;
//            Predicate<Prietenie> verify2 = x -> x.getId().getLeft() == id;
//            repoPrietenie.findAll().forEach(x ->
//            {
//                if (verify.test(x) == true) {
//                    Long abc = x.getId().getLeft();
//                    Long abd = x.getId().getRight();
//                    delete_friendship(abc, abd);
//
//                }
//                if (verify2.test(x) == true) {
//                    Long abc = x.getId().getLeft();
//                    Long abd = x.getId().getRight();
//                    delete_friendship(abc, abd);
//                }
//            });
//        } catch (Exception e) {
//            repoUtilizatori.delete(id);
//        }
//        repoUtilizatori.delete(id);
    }

    public void add_friendship(Long id1,Long id2)
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




      u1.addFriend(u2);
      u2.addFriend(u1);
      Prietenie p = new Prietenie();
      p.setId(tup);
      repoPrietenie.save(p);
      repoPrietenie.findAll().forEach(System.out::println);
    }




    public Iterable<Prietenie> get_prietenii(){
        return repoPrietenie.findAll();
    }

    public void delete_friendship(Long id1, Long id2)
    {
        Utilizator uwantdell  = repoUtilizatori.findOne(id1);
        Utilizator udeleted = repoUtilizatori.findOne(id2);

        if(uwantdell==null)
            throw new ValidationException("no user with id1");
        if(udeleted == null )
            throw new ValidationException("no user with id2");

            repoPrietenie.delete(new Tuple(id1,id2));
            uwantdell.remove_Friend(udeleted);
            udeleted.remove_Friend(uwantdell);


        Iterable<Request> req =  repoRequest.findAll();
        for(Request r:req)
        {
            if(r.getId() == new Tuple(id1,id2))
                repoRequest.delete(r.getId());
            else if(r.getId() == new Tuple(id2,id1))
                repoRequest.delete(r.getId());
        }

    }

    public Utilizator get_user_data(Long id)
    {
        if(repoUtilizatori.findOne(id)==null)
            throw new ValidationException("no user with this id");
        return repoUtilizatori.findOne(id);
    }



    public HashMap<Long, ArrayList<Long>> make_graph()
    {
        HashMap dict = new HashMap<Long, ArrayList<Long>>();


        repoUtilizatori.findAll().forEach(e->{
            if(!dict.containsKey(e.getId()))
                dict.put(e.getId(),new ArrayList<Long>());
        });


        repoPrietenie.findAll().forEach(e->{

           if(!dict.containsKey(e.getId().getLeft()))
               dict.put(e.getId().getLeft(),new ArrayList<Long>());

           if(!dict.containsKey(e.getId().getRight()))
               dict.put(e.getId().getRight(),new ArrayList<Long>());

        });
        repoPrietenie.findAll().forEach(e-> {
            ArrayList<Long> list1 = new ArrayList<Long>();
            list1 = (ArrayList<Long>) dict.get(e.getId().getLeft());

            ArrayList<Long> list2 = new ArrayList<Long>();
            list2 = (ArrayList<Long>) dict.get(e.getId().getRight());


            // i add to the first el of tupple

            list1.add(e.getId().getRight());
            dict.put(e.getId().getLeft(),list1);


            // i add to the second el of tuple

            list2.add(e.getId().getLeft());
            dict.put(e.getId().getRight(),list2);

       });


        System.out.println(dict);

        return dict;
    }

    public ArrayList<Long> dfs( HashMap<Long,Boolean> visited ,Long s)
    {
        HashMap<Long, ArrayList<Long>> graph = make_graph();

        Stack<Long> st = new Stack();
        ArrayList<Long> ar = new ArrayList<Long>();

        st.push(s);
        while(!st.isEmpty())
        {
            Long el = st.pop();
            // if is not in visited

            if(!visited.get(el))
            {
                visited.put(el,true);
                ar.add(el);
                System.out.print(el + " ");
            }

            for(Long vertex: graph.get(el))
                if(!visited.get(vertex))
                    st.push(vertex);

        }
        System.out.println("////");
        return ar;
    }

    ///TO DO: add other methods


    public int get_nr_connected_components()
    {
        HashMap<Long, ArrayList<Long>> dict = make_graph();

        HashMap<Long,Boolean> visited = new HashMap<Long,Boolean>();



        for(HashMap.Entry<Long,ArrayList<Long>> entry: dict.entrySet())
        {
                visited.put(entry.getKey(),false);
        }
        System.out.println(visited);
        Integer  nr_cc =0;


        for(HashMap.Entry<Long,Boolean> entry: visited.entrySet())
        {
            System.out.println(entry.getValue());
            if (entry.getValue() == false)
            {
                System.out.println("DDDD");
                System.out.println(visited);
                System.out.println(entry.getKey());
                dfs(visited,entry.getKey());
                nr_cc+=1;
            }
        }

        System.out.println("NRRR");
        System.out.println(nr_cc);

        return nr_cc;
    }


    public ArrayList<Long> most_sociable_comunity()
    {
        HashMap<Long, ArrayList<Long>> dict = make_graph();

        HashMap<Long,Boolean> visited = new HashMap<Long,Boolean>();

        ArrayList<Long> comunity = new ArrayList<Long>();
        ArrayList<Long> afterdfs= new ArrayList<Long>();



        for(HashMap.Entry<Long,ArrayList<Long>> entry: dict.entrySet())
        {
            visited.put(entry.getKey(),false);
        }
        System.out.println(visited);
        ;


        for(HashMap.Entry<Long,Boolean> entry: visited.entrySet())
        {
            System.out.println(entry.getValue());
            if (entry.getValue() == false)
            {

                System.out.println(visited);
                System.out.println(entry.getKey());

                afterdfs = dfs(visited,entry.getKey());
                if(afterdfs.size()>comunity.size())
                    comunity = afterdfs;

            }
        }
        return comunity;
    }





}
