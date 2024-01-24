package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.MessageService;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.RequestService;
import socialnetwork.service.UtilizatorService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddDeleteFriendController {
    private UtilizatorService srv;
    private PrietenieService srvPrietenie;
    private MessageService srvMessaj;
    private RequestService srvRquest;
    private Long idUserLongged;
    private ObservableList<Utilizator> observaleList = FXCollections.observableArrayList();
    private ObservableList<Prietenie> observaleListData = FXCollections.observableArrayList();

    @FXML
    private TableView table;
    @FXML
    private TextField nume;

    @FXML
    private Label erori;

    @FXML
    private TableColumn firstName;

    @FXML
    private TableColumn lastName;

    @FXML
    public void initialize()
    {
        firstName.setCellValueFactory(new PropertyValueFactory<Utilizator,String>("firstName"));
//      data.setCellValueFactory(new PropertyValueFactory<Utilizator,String>("lastName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Utilizator,String>("lastName"));
        table.setItems(observaleList);

    }





    public void friend_list()
    {   observaleListData.removeAll();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        ArrayList<Utilizator> lista = new ArrayList<Utilizator>();
        for(Tuple<Utilizator,Prietenie> t: srvPrietenie.get_friends_list(idUserLongged)){
            String nume = t.getLeft().getFirstName() + " " +t.getLeft().getLastName();
            String data = t.getRight().getDate().format(formatter);

            lista.add(t.getLeft());
        }

        observaleList.setAll(lista);
//

        System.out.println(observaleList);

    }

    public void initial(Long idUserLongged ,UtilizatorService srvU, PrietenieService srvP, MessageService srvMessaj, RequestService srvRequest) {
        this.idUserLongged=idUserLongged;
        this.srv = srvU;
        this.srvPrietenie = srvP;
        this.srvMessaj = srvMessaj;
        this.srvRquest = srvRequest;
        friend_list();

    }

    public void addFriend(MouseEvent mouseEvent) {
        erori.setText("");
        String name=nume.getText();
        if(name.isEmpty()){
            erori.setText("filed is empty");
            return;
        }
        Utilizator user_cautat=null;
        for(Utilizator u: srv.getAll()){
            if(u.getFirstName().equals(name))
                user_cautat=u;
        }
        if(user_cautat==null)
        {
            erori.setText("This user dont exists");
            return;
        }

        for(Tuple<Utilizator,Prietenie> t: srvPrietenie.get_friends_list(idUserLongged)){

           if (t.getLeft().equals(user_cautat)) {
               erori.setText("this friend already exists");
                return;
           }
        }




       // srv.add_friendship(idUserLongged,user_cautat.getId());
        srvRquest.send_request(idUserLongged,user_cautat.getId());
        friend_list();
    }

    public void delete(ActionEvent actionEvent) {
        Utilizator selected = (Utilizator) table.getSelectionModel().getSelectedItem();
        if(selected !=null)
        {
            try {
                srv.delete_friendship(idUserLongged,selected.getId());

            }catch(Exception e)
            {
                srv.delete_friendship(selected.getId(),idUserLongged);
            }

        }
        friend_list();
    }
}



//    public void delete_friend(ActionEvent actionEvent) {
//        Utilizator selected=(Utilizator) tabel_prietenii.getSelectionModel().getSelectedItem();
//
//
//        if(selected!=null)
//        {
//            int ok=0;
//            List<Utilizator> list=new ArrayList<Utilizator>();
//            service.getAll().forEach(x->
//            {
//                if(x.getEmail().equals(email)== true && x.getPassword().equals(password)==true)
//                {
//                    list.add(x);
//                }
//            });
//
//            if(list.size()>1)
//            {
//                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "enter complete name", "there are many clients with this name,enter full name please");
//
//            }
//            else {
//                Utilizator a=list.get(0);
//                try {
//                    service.delete_friendship(a.getId(), selected.getId());
//                } catch (Exception e) {
//                    ok += 1;
//
//
//                }
//                try {
//                    service.delete_friendship(selected.getId(), a.getId());
//                } catch (Exception e) {
//                    ok += 1;
//
//
//                }
//                if (ok <= 1) {
//                    MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "done", "friendship deleted");
//                    modelGrade.setAll(getUtilizatorDTOList());
//                } else {
//                    MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "error", "can't delete");
//                }
//            }
//        }
//        else
//        {
//            MessageAlert.showMessage(null,Alert.AlertType.INFORMATION,"error","nothing selected");
//        }
//    }