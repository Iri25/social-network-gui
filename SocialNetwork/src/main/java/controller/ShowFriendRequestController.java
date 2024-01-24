package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import socialnetwork.domain.Request;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.MessageService;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.RequestService;
import socialnetwork.service.UtilizatorService;

import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ShowFriendRequestController {

    private ObservableList<ReqUser> observaleList = FXCollections.observableArrayList();
    @FXML
    public TableColumn <ReqUser, String> status;
    @FXML
    public TableColumn<ReqUser,String> name;
    @FXML
    public TableView table;
    @FXML
    private TextArea textArea;

    @FXML
    public void initialize()
    {



        name.setCellValueFactory(new PropertyValueFactory<ReqUser,String>("nume"));
        status.setCellValueFactory(new PropertyValueFactory<ReqUser,String>("status"));



        table.setItems(observaleList);
    }



    private UtilizatorService srv;
    private PrietenieService srvPrietenie;
    private MessageService srvMessaj;
    private RequestService srvRquest;
    private Long idUserLongged;




    public void initial(Long idUserLongged ,UtilizatorService srvU, PrietenieService srvP, MessageService srvMessaj, RequestService srvRequest) {
        this.idUserLongged=idUserLongged;
        this.srv = srvU;
        this.srvPrietenie = srvP;
        this.srvMessaj = srvMessaj;
        this.srvRquest = srvRequest;

        refresh();
    }


    public void refresh(){
        Iterable<Request> friendRequests = srvRquest.getAll();
        Iterable<Request> friendRequestsbuni=new ArrayList<>();

        friendRequestsbuni=StreamSupport.stream(friendRequests.spliterator(), false)
                .filter(x-> x.getId().getRight().equals(idUserLongged) || x.getId().getLeft().equals(idUserLongged) )
                .collect(Collectors.toList());
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


        System.out.println("DNDNASDNASJKDASKLJD");
        ArrayList<Tuple<Utilizator,Request>> lis = new ArrayList<Tuple<Utilizator,Request>>();
        friendRequestsbuni.forEach(x->{

            if (srv.getOne(x.getId().getLeft()).getId() == idUserLongged)
            {
                lis.add(new Tuple(srv.getOne(x.getId().getRight()),x));
//                textArea.appendText(srv.getOne(x.getId().getRight()).getFirstName() + " " + x.getStatus() + " " + x.getdata().format(formatter) + "\n");

            }
            else
            {
                lis.add(new Tuple(srv.getOne(x.getId().getLeft()),x));
//                textArea.appendText(srv.getOne(x.getId().getLeft()).getFirstName() + " " + x.getStatus() + " " + x.getdata().format(formatter) + "\n");
            }
        });

        System.out.println(lis);


        ArrayList<ReqUser> re = new ArrayList<ReqUser>();
        for (Tuple<Utilizator, Request> li : lis) {

            ReqUser r = new ReqUser(li.getRight().getId(),li.getLeft().getId(), li.getLeft().getFirstName(),li.getRight().getStatus());
            re.add(r);
        }

        System.out.println(re);

        observaleList.setAll(re);
    }




    public void check_update(MouseEvent mouseEvent) {
    }

    public void accept_btn(MouseEvent mouseEvent) {
        ReqUser selected = (ReqUser) table.getSelectionModel().getSelectedItem();
        if(selected !=null)
        {
            if ( selected.getStatus().equals("pending"))
            {
                 Request r = srvRquest.getOne(selected.getIdRequest());

                 srvRquest.accept_friend_request(r.getWhoRequested(),r.getWhoRecived());


            }

        }
        refresh();
    }



    public void reject_btn(MouseEvent mouseEvent) {

        ReqUser selected = (ReqUser) table.getSelectionModel().getSelectedItem();
        if(selected !=null)
        {
            if ( selected.getStatus().equals("declined"))
            {
                Request r = srvRquest.getOne(selected.getIdRequest());
                srvRquest.decline_friend_request(r.getWhoRequested(),r.getWhoRecived());

            }

        }
        refresh();
    }
}
