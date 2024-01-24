package socialnetwork;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import socialnetwork.config.ApplicationContext;
import socialnetwork.domain.*;
import socialnetwork.domain.validators.PrietenieValidator;
import socialnetwork.domain.validators.UtilizatorValidator;
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




public class MainFX extends Application {
    private UtilizatorService srv=null;
    private PrietenieService srvFriend=null;
    private RequestService srvRequest=null;
    private MessageService srvMessage=null;



    public void init_srv()
    {
        System.out.println("dada");
        String fileName    = ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.users");
        // String prieteniName=ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.prietenii");
        // String fileName="data/users.csv";
        String prieteniName = "data/prietenii.csv";
        System.out.println("READING data from database");
        final String url = ApplicationContext.getPROPERTIES().getProperty("database.socialnetwork.url");
        final String username="postgres";
        final String pasword= ApplicationContext.getPROPERTIES().getProperty("database.socialnetwork.password");
        Repository<Long,Utilizator> databaseUserRepository =
                new UtilizatorDbRepository(url,username, pasword,  new UtilizatorValidator());


        Repository<Tuple<Long,Long>,Prietenie> databasePrietenieRepository =
                new PrietenieDbRepository(url,username, pasword,  new UtilizatorValidator());


        Repository<Long, Message> databaseMessageRepository =
                new MessageDbRepository(url,username,pasword);

        Repository<Tuple<Long,Long>, Request> databaseRequestRepository=
                new RequestDbRepository(url,username,pasword);



         srv = new UtilizatorService(databaseUserRepository,databasePrietenieRepository,databaseRequestRepository);
         srvFriend = new PrietenieService(databaseUserRepository,databasePrietenieRepository,databaseRequestRepository);
         srvMessage = new MessageService(databaseMessageRepository,databaseUserRepository);
         srvRequest = new RequestService(databaseRequestRepository,databaseUserRepository,databasePrietenieRepository);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        init_srv();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Login.fxml"));
        Pane root;
        root = loader.load();




        LoginController ctr = loader.getController();
        ctr.initial(srv,srvFriend,srvMessage,srvRequest);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Program");
        primaryStage.show();

    }
    public static void main(String[] args){

        launch();

    }
}
