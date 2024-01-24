package controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import socialnetwork.service.MessageService;
import socialnetwork.service.PrietenieService;
import socialnetwork.service.RequestService;
import socialnetwork.service.UtilizatorService;

public class MenuMainController {
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
    }

    public void openAddDeleteFriend(MouseEvent mouseEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/AddDeleteFriend.fxml"));
        Pane root;
        root = loader.load();

        Stage register_stage=new Stage();
        AddDeleteFriendController adController = loader.getController();
        adController.initial(idUserLongged,srv,srvPrietenie,srvMessaj,srvRquest);
        register_stage.setScene(new Scene(root));
        register_stage.show();
    }

    public void OpenShowFriendRequest(MouseEvent mouseEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ShowFriendRequests.fxml"));
        Pane root;
        root = loader.load();

        Stage register_stage=new Stage();
        ShowFriendRequestController sfrController=loader.getController();
        sfrController.initial(idUserLongged,srv,srvPrietenie,srvMessaj,srvRquest);
        register_stage.setScene(new Scene(root));
        register_stage.show();
    }
}
