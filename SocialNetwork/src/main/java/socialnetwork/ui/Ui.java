package socialnetwork.ui;
import socialnetwork.domain.Message;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.service.MessageService;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.RequestService;
import socialnetwork.service.UtilizatorService;
import socialnetwork.domain.validators.ValidatorUi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Ui {
    private UtilizatorService srv;
    private PrietenieService srvPrietenie;
    private MessageService srvMessage;
    private RequestService srvRequest;
    private Scanner scanner = new Scanner(System.in);
    private ValidatorUi valUi = new ValidatorUi();

    public Ui(UtilizatorService srv, PrietenieService srvPrietenie,MessageService srvMessage,RequestService srvRequest)
    {
        this.srv = srv;
        this.srvPrietenie = srvPrietenie;
        this.srvMessage = srvMessage;
        this.srvRequest = srvRequest;
    }

    public void printMenu(){
        System.out.println("cmd for app (press)");
        System.out.println("add user: 1");
        System.out.println("print list : 2 ");
        System.out.println("delete user: 3 ");
        System.out.println("add friends for a user: 4");
        System.out.println("unfriend someone 5:");
        System.out.println("print friend lists 6: ");
        System.out.println("print the numbers of communities 7:");
        System.out.println("print most sociable community 8:");
        System.out.println("print friend list for a user 9:");
        System.out.println("print friend list by month for a user 10:");
        System.out.println("print message list 11: ");
        System.out.println("print a message by id:12 ");
        System.out.println("send a message 13: ");
        System.out.println("reply to a message 14: ");
        System.out.println("print chronologically 2 users messages 15: ");
        System.out.println("send a friend request 16: ");
        System.out.println("accept a friend request 17: ");
        System.out.println("decline a friend request 18: ");


        System.out.println("exit:0");

    }

    public void addUser()
    {
        String nume,prenume;
        String id;



        System.out.println("Insert first name:");
        prenume = scanner.next();
        valUi.validate_name(prenume);

        System.out.println("Insert last name:");
        nume = scanner.next();
        valUi.validate_name(nume);

        System.out.println("insert ID:");
        id = scanner.next();
        valUi.validate_id(id);

        Utilizator ut1 = new Utilizator(prenume,nume);

        // pot sa ii dau deja asa pentru ca deja e validat ca si long
        ut1.setId(Long.parseLong(id));

        srv.addUtilizator(ut1);
        System.out.println("user added");

    }

    public void delete_user()
    {
        System.out.println("insert ID for del:");
        String id;
        id = scanner.next();
        valUi.validate_id(id);

        srv.delete_user(Long.parseLong(id));
    }

    public void print_list(){

        srv.getAll().forEach(System.out::println);
    }

    public void print_friendlist()
    {
        srv.get_prietenii().forEach(System.out::println);

    }

    public void add_friendship()
    {

        String id1,id2;
        System.out.println("insert first friend id: ");
        id1 = scanner.next();
        valUi.validate_id(id1);
        System.out.println("insert second friend id: ");
        id2 = scanner.next();
        valUi.validate_id(id2);


        srv.add_friendship(Long.parseLong(id1),Long.parseLong(id2));
        System.out.println("friend added");
    }

    public void delete_friendship()
    {
        String id1,id2;
        System.out.println("id for person who want to delete a friend: ");
        id1 = scanner.next();
        valUi.validate_id(id1);
        System.out.println("id of the person ID1 want to unfriend: ");
        id2 = scanner.next();
        valUi.validate_id(id2);

        srv.delete_friendship(Long.parseLong(id1),Long.parseLong(id2));
        System.out.println("unfriended");
    }


    public void print_comunities()
    {
        System.out.println("Number of comunities is: " +srv.get_nr_connected_components());
    }

    public void most_sociable_comunity()
    {
        System.out.println("Most sociable comunity is: " + srv.most_sociable_comunity());
    }


    public void user_friends()
    {
        String id1;
        System.out.println("id for person: ");
        id1 = scanner.next();
        valUi.validate_id(id1);
        srvPrietenie.get_friends_list(Long.parseLong(id1)).forEach(x->{
            System.out.println(x);
        });
    }

    public void user_friends_by_month()
    {
        String id1;
        String luna;
        System.out.println("id for person: ");
        id1 = scanner.next();
        valUi.validate_id(id1);
        System.out.println("month for firendship: ");
        luna = scanner.next();

        srvPrietenie.get_friends_list_by_month(Long.parseLong(id1),luna).forEach(x->{
            System.out.println(x);
        });

    }

    public void print_messages()
    {
        srvMessage.getAll().forEach(x-> System.out.println(x));
    }

   public void print_message_from_user()
   {
       String id1;
       System.out.println("id for message: ");
       id1 = scanner.next();
       valUi.validate_id(id1);
       System.out.println(srvMessage.get_message_for_user(Long.parseLong(id1)));
   }

   public void send_message()
   {
       String id_mesaj;
       System.out.println("id pentru mesaj: ");
       id_mesaj = scanner.next();
       valUi.validate_id(id_mesaj);

       String id_exp;
       System.out.println("id for expeditor");
        id_exp = scanner.next();
        valUi.validate_id(id_exp);

       System.out.println("Type a message: ");
       String message;
       message = scanner.next();


        boolean run =true;
       System.out.println("introduce id for destination (0 if u finish): ");

       ArrayList<Long> lista_destinatari = new ArrayList<Long>();
       while(run)
       {String id_dest;
           System.out.println("id:");
           id_dest = scanner.next();
           valUi.validate_id(id_dest);
           if(id_dest.equals("0"))
               break;
           else{
                lista_destinatari.add(Long.parseLong(id_dest));
           }
       }

        srvMessage.send_message(Long.parseLong(id_mesaj),Long.parseLong(id_exp),lista_destinatari,message);
   }

   public void reply_message()
   {
       String id_msg_to_rply;
       System.out.println("id for message you want to reply: ");
       id_msg_to_rply = scanner.next();
       valUi.validate_id(id_msg_to_rply);

       System.out.println("new id for the message: ");
       String new_id  = scanner.next();
       valUi.validate_id(new_id);

       String id_who_rply;
       System.out.println("id for the person who wants to reply: ");
       id_who_rply= scanner.next();
       valUi.validate_id(id_who_rply);

       System.out.println("type 1-> Reply all" );
       System.out.println("type 2-> Reply to a single user");

String cmd;
cmd = scanner.next();
        if(cmd.equals("1"))
        {
            String msg_rply;
            System.out.println("message for reply: ");
            msg_rply = scanner.next();

            srvMessage.reply_all(Long.parseLong(new_id),Long.parseLong(id_msg_to_rply),Long.parseLong(id_who_rply),msg_rply);

        }
        else if(cmd.equals("2"))
        {
            String msg_rply;
            System.out.println("message for reply:");
            msg_rply = scanner.next();

            System.out.println("id for the persons you want to reply (if 0 exit) : ");
            ArrayList<Long> lista_destinatari = new ArrayList<Long>();
            while(true)
            {String id_dest;
                System.out.println("id:");
                id_dest = scanner.next();
                valUi.validate_id(id_dest);
                if(id_dest.equals("0"))
                    break;
                else{
                    lista_destinatari.add(Long.parseLong(id_dest));
                }
            }

            srvMessage.reply_filter(Long.parseLong(new_id),Long.parseLong(id_msg_to_rply),Long.parseLong(id_who_rply),msg_rply,lista_destinatari);

        }
            else{
       System.out.println("Type 1 or 2...");
        }


       System.out.println("message replied");
   }

public Utilizator get_user(List<Utilizator> lista,Long id)
{
    for(Utilizator u:lista)
    {
        if(u.getId() == id)
            return u;
    }
    return null;

}


   public void cronologic_message()
   {
       System.out.println("id for user 1: ");
       String id1 = scanner.next();
       valUi.validate_id(id1);
       System.out.println("id for user 2: ");
       String id2 = scanner.next();
       valUi.validate_id(id2);

    List<Message> mesaje = srvMessage.cronologic_message(Long.parseLong(id1),Long.parseLong(id2));

       System.out.println("aiciciciciicic");
    mesaje.forEach(x->
    {
        if(x.get_from().getId() == Long.parseLong(id1))
             System.out.println(x.get_from().getId() +":"+x.get_from().getFirstName() +
                     " " + x.get_from().getLastName() + " catre " + id2 + ":"
                     + get_user(x.get_to(),Long.parseLong(id2)).getFirstName() + " " +
                     get_user(x.get_to(),Long.parseLong(id2)).getLastName()
                    + "  MESAJ:  " + x.get_message());


        else if(x.get_from().getId() == Long.parseLong(id2))
            System.out.println(x.get_from().getId() +":"+x.get_from().getFirstName() +
                    " " + x.get_from().getLastName() + " catre " + id1 + ":"
                    + get_user(x.get_to(),Long.parseLong(id1)).getFirstName() + " " +
                    get_user(x.get_to(),Long.parseLong(id1)).getLastName()
                    + "  MESAJ:  " + x.get_message());

    });


   }

   public void send_friend_request()
   {
       System.out.println("id for user who make the req: ");
       String id_req = scanner.next();
       valUi.validate_id(id_req);

       System.out.println("id for the user that recive : ");
       String id_recive = scanner.next();
       valUi.validate_id(id_recive);


       srvRequest.send_request(Long.parseLong(id_req),Long.parseLong(id_recive));
       System.out.println("request sended");


   }
public void accept_friend_request(){
    System.out.println("id for the person who want to accept the req: ");
    String id1= scanner.next();
    valUi.validate_id(id1);

    System.out.println("id for the person that ID1 accepts request : ");
    String id2= scanner.next();
    valUi.validate_id(id2);

    srvRequest.accept_friend_request(Long.parseLong(id1),Long.parseLong(id2));
    System.out.println("friend request accepted");


}

public void decline_friend_requst()
{
    System.out.println("if for the person who want to decline the req: ");
    String id1= scanner.next();
    valUi.validate_id(id1);

    System.out.println("id for the person that ID1 decline request : ");
    String id2= scanner.next();
    valUi.validate_id(id2);

    srvRequest.decline_friend_request(Long.parseLong(id1),Long.parseLong(id2));
    System.out.println("friend request declined :(");

}


    public void run()
    {
        boolean running = true;
        while(running)
        {
            printMenu();
            String cmd;
            System.out.println("choose comand: ");

            cmd = scanner.next();
            try{
                switch(cmd) {
                    case("0"): running=false;
                        break;

                    case ("1"): addUser();
                        break;
                    case("2"): print_list();
                        break;
                    case("3"): delete_user();
                        break;
                    case("4"): add_friendship();
                        break;
                    case("5"):delete_friendship();
                        break;
                    case("6"):print_friendlist();
                        break;
                    case("7"):print_comunities();
                        break;
                    case("8"):most_sociable_comunity();
                        break;
                    case("9"):user_friends();
                        break;
                    case("10"):user_friends_by_month();
                        break;
                    case("11"):print_messages();
                        break;
                    case("12"):print_message_from_user();
                            break;
                    case("13"):send_message();
                            break;
                    case("14"):reply_message();
                        break;
                    case("15"):cronologic_message();
                        break;
                    case("16"):send_friend_request();
                        break;
                    case("17"):accept_friend_request();
                         break;
                    case("18"):decline_friend_requst();
                        break;
                    default:

                        System.out.println("Invalid cmd");
                        break;
                }
            }catch(ValidationException ex )
            {
                System.out.println(ex.getMessage());
            }
            catch(IllegalArgumentException ex)
            {
                System.out.println(ex.getMessage());
            }



        }
    }

}
