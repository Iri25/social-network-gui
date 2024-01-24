package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.MessageService;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.RequestService;
import socialnetwork.service.UtilizatorService;





import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.animation.*;
import javafx.stage.Stage;
import javafx.util.Duration;



public class LoginController {
    public Label erroruser;
    public Label errorpassword;
    @FXML
    private TextField user123;
    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane anchorPane;
    private UtilizatorService srv;
    private PrietenieService srvPrietenie;
    private MessageService srvMessaj;
    private RequestService srvRquest;


    public void initial( UtilizatorService srvU, PrietenieService srvP, MessageService srvMessaj, RequestService srvRequest) {

        this.srv = srvU;
        this.srvPrietenie = srvP;
        this.srvMessaj = srvMessaj;
        this.srvRquest = srvRequest;
    }


    public void login123(MouseEvent mouseEvent) throws Exception{
        //caut utilizatorul cu care ma loghez
        erroruser.setText("");
        errorpassword.setText("");

        String u = user123.getText();
        String pass = password.getText();
        if(u.isEmpty() || pass.isEmpty())
            errorpassword.setText("Nu puteti lasa nici un camp necompletat!");
        Utilizator ut= null;
        Iterable<Utilizator> lis = srv.getAll();
        for (Utilizator utilizator : lis) {
            if(utilizator.getusername().equals(u) && utilizator.getpassword().equals(pass))
                ut = utilizator;

        }
        if(ut==null){
            erroruser.setText("Invalid account");
            return;
        }
        //aici ajung doar daca s-a gasit utilizatorul
        //deschid meniul principal
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/MenuMain.fxml"));
        Pane root;
        root = loader.load();

        Stage register_stage=new Stage();
        MenuMainController menuMainController=loader.getController();
        menuMainController.initial(ut.getId(),srv,srvPrietenie,srvMessaj,srvRquest);
        register_stage.setScene(new Scene(root));
        register_stage.show();
    }


    public void register(MouseEvent mouseEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Register.fxml"));
        Pane root;
        root = loader.load();

        Stage register_stage=new Stage();
        RegisterController regCtr = loader.getController();
        regCtr.initial(this.srv,this.srvPrietenie,this.srvMessaj,this.srvRquest);
        register_stage.setScene(new Scene(root));
        register_stage.show();
    }
}


