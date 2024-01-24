package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.MessageService;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.RequestService;
import socialnetwork.service.UtilizatorService;

public class RegisterController {
    private UtilizatorService srv;
    private PrietenieService srvPrietenie;
    private MessageService srvMessaj;
    private RequestService srvRquest;

    @FXML
    private TextField prenume;

    @FXML
    private TextField nume;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private Button create;

    @FXML
    private Label erori;

    public void initial(UtilizatorService srvU, PrietenieService srvP, MessageService srvMessaj, RequestService srvRequest) {

        this.srv = srvU;
        this.srvPrietenie = srvP;
        this.srvMessaj = srvMessaj;
        this.srvRquest = srvRequest;
    }


    public void create(MouseEvent mouseEvent) {
         Long max = -10L;

         //generate next id
        Iterable<Utilizator> lis = srv.getAll();
        for (Utilizator utilizator : lis) {
           if(max<utilizator.getId())
               max = utilizator.getId();
        }
        Long new_id = max+1l;
        erori.setText("");

        //datele din textFielduri
        String surname = prenume.getText();
        String name = nume.getText();
        String usrname = username.getText();
        String pass = password.getText();
        String mail = email.getText();
        if (surname.isEmpty() || name.isEmpty() || usrname.isEmpty() || pass.isEmpty() || mail.isEmpty()) {
            erori.setText("Nu puteti lasa nici un camp necompletat!!!");
            return;
        }
        Utilizator newUser = new Utilizator(surname, name, usrname, pass, mail);
        newUser.setId(new_id);
        srv.addUtilizator(newUser);
        erori.setText("User nou adaugat!");
    }
}
