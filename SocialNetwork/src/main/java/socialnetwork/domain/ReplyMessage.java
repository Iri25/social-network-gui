package socialnetwork.domain;

import java.util.List;

public class ReplyMessage extends Message{
    private Message reply;

    public ReplyMessage(Utilizator from, List<Utilizator> to, String message) {
        super(from, to, message);
    }
    @Override
    public Long getReply() {
        return reply.getId();
    }


    public void setReply(Message m){this.reply = m;}

}
