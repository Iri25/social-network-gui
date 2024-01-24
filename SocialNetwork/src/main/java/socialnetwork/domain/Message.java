package socialnetwork.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Message extends Entity<Long> {
    private Utilizator from;
    private List<Utilizator> to;
    private String message;
    private LocalDateTime data;
    private Long id_mesaj;

    public Message(Utilizator from,List<Utilizator> to, String message)
    {
        this.from = from;
        this.to = to;
        this.message = message;
        this.data = LocalDateTime.now();
    }
    public Utilizator get_from() {return this.from;}
    public List<Utilizator> get_to() {return this.to;}
    public LocalDateTime getData(){return this.data;}
    public String get_message(){return this.message;}
    public Long getReply(){return 0L;}

    @Override
    public String toString(){
        List<Long>lst=new ArrayList<Long>();
        for(Utilizator ut:to)
            lst.add(ut.getId());
        String msg=""+this.getId()+";"+from.getId()+";"+lst+";"+data+";"+message;

        return msg;
    }

}

