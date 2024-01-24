package socialnetwork;

import socialnetwork.config.ApplicationContext;
import socialnetwork.domain.*;
import socialnetwork.domain.validators.PrietenieValidator;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.repository.Repository;
import socialnetwork.repository.database.MessageDbRepository;
import socialnetwork.repository.database.PrietenieDbRepository;
import socialnetwork.repository.database.RequestDbRepository;
import socialnetwork.repository.database.UtilizatorDbRepository;
import socialnetwork.repository.file.PrietenieFile;
import socialnetwork.repository.file.UtilizatorFile;
import socialnetwork.service.MessageService;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.RequestService;
import socialnetwork.service.UtilizatorService;

import socialnetwork.ui.Ui;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;


import socialnetwork.domain.validators.ValidatorUi;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {



        MainFX gui = new MainFX();
        gui.main(args);

//        String luna = "may12";
//
//
//        Month en = Month.valueOf(luna.toUpperCase());
//        System.out.println(en);

//    UtilizatorValidator val = new UtilizatorValidator();
//    Utilizator v = new Utilizator("gep","ca");
//    val.validate(v);
//Map dict = new HashMap<Long,ArrayList<Integer>>();
//ArrayList<Integer> l = new ArrayList<Integer>();
//l.add(1);
//l.add(2);
//l.add(3);
//dict.put(1,l);
//
//ArrayList li= (ArrayList) dict.get(1);
//
//
//
//        li.add(12);
//dict.put(1,li);
//
//System.out.println(dict);
//        Utilizator ut0 = new Utilizator("ana","dana");
//        ut0.setId(1L);
//        Utilizator ut1 = new Utilizator("A","b");
//        ut0.setId(2L);
//        Utilizator ut2 = new Utilizator("c","d");
//        ut0.setId(3L);
//        ArrayList<Utilizator> listuser = new ArrayList<Utilizator>();
//        listuser.add(ut1);
//        listuser.add(ut2);
//        listuser.add(ut0);


//        Message ms = new Message(ut0,listuser,"caca");
//        System.out.println(ms);




//
//
//        String fileName    =ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.users");
//        //String prieteniName=ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.prietenii");
//        //String fileName="data/users.csv";
//        String prieteniName = "data/prietenii.csv";
//       Repository<Long,Utilizator> userFileRepository = new UtilizatorFile(fileName, new UtilizatorValidator());
//
//       userFileRepository.findAll();
//
//       Repository<Tuple<Long,Long>,Prietenie> prietenieFileRepository = new PrietenieFile(prieteniName,new PrietenieValidator());
//
//
//        String s;
//
//        System.out.println("READING data from database");
//        final String url = ApplicationContext.getPROPERTIES().getProperty("database.socialnetwork.url");
//        final String username="postgres";
//        final String pasword= ApplicationContext.getPROPERTIES().getProperty("database.socialnetwork.password");
//        Repository<Long,Utilizator> databaseUserRepository =
//                new UtilizatorDbRepository(url,username, pasword,  new UtilizatorValidator());
//
//
//        Repository<Tuple<Long,Long>,Prietenie> databasePrietenieRepository =
//                new PrietenieDbRepository(url,username, pasword,  new UtilizatorValidator());
//
//
//        Repository<Long, Message> databaseMessageRepository =
//                new MessageDbRepository(url,username,pasword);
//
//        Repository<Tuple<Long,Long>, Request> databaseRequestRepository=
//                new RequestDbRepository(url,username,pasword);
//
//
//
//        UtilizatorService srv = new UtilizatorService(databaseUserRepository,databasePrietenieRepository,databaseRequestRepository);
//        PrietenieService srvPrietenie = new PrietenieService(databaseUserRepository,databasePrietenieRepository,databaseRequestRepository);
//        MessageService srvMessage = new MessageService(databaseMessageRepository,databaseUserRepository);
//        RequestService srvRequest = new RequestService(databaseRequestRepository,databaseUserRepository,databasePrietenieRepository);
//
//       // UtilizatorService srv = new UtilizatorService(userFileRepository,prietenieFileRepository);
//        System.out.println("//////////////");
////        srv.make_graph();
//
//
//        System.out.println(prietenieFileRepository.findAll());
//        List <Prietenie> lista = new ArrayList<Prietenie>();
//
//
//
//        Iterable<Prietenie> lista1 = prietenieFileRepository.findAll();
//        System.out.println(lista1);
//
//
//
//
////        List <Prietenie> lista2 = new ArrayList<Prietenie>();
////        Iterable<Prietenie> pr = prietenieFileRepository.findAll();
////        pr.forEach(x->lista.add((Prietenie) x));
////
////      lista2  =  lista.stream().filter(p-> p.getId().getLeft() ==1 || p.getId().getRight() ==1
////          ).collect(toList());
////
////        System.out.println(lista2);
//
////        List<Tuple<Utilizator, LocalDateTime>> fin = new ArrayList<Tuple<Utilizator, LocalDateTime>>();
////        fin = lista2.stream().collect(toList(
////               // p-> (p.getId().getLeft())
////        ))
//
//        Ui ui = new Ui(srv,srvPrietenie,srvMessage,srvRequest);
//
//        ui.run();
//
//        userFileRepository.findAll().forEach(System.out::println);
//        prietenieFileRepository.findAll().forEach(el-> System.out.println(el));



    }
}


