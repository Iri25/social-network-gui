package controller;

import javafx.scene.control.Button;
import socialnetwork.domain.Tuple;

public class ReqUser {
    private String nume;
    private  String status;
    private Long idUser;
    private Tuple<Long,Long> idRequest;

    public ReqUser(Tuple<Long,Long> idReq ,Long iduser ,String nume,String status)
    {
        this.idUser = iduser;
        idRequest = idReq;
        this.nume = nume;
        this.status = status;
//        this.status.setOnAction(e->{
//
//            this.stauts_check();
//        });


    }
    public Tuple<Long,Long> getIdRequest()
    {
        return this.idRequest;
    }

    public String getStatus(){return this.status;}

    public String getNume() {
        return nume;
    }

    public Long getIdUser() {
        return idUser;
    }




}
