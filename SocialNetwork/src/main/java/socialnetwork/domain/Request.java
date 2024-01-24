package socialnetwork.domain;

import java.time.LocalDateTime;

public class Request extends Entity<Tuple<Long,Long>> {
     private String status ="pending";
     private LocalDateTime data;
     public Request(Long id1, Long id2) {setId( new Tuple(id1,id2));
     this.data = LocalDateTime.now();
     }


    public Request(Long id1, Long id2, String stat, LocalDateTime data) {
         setId( new Tuple(id1,id2));
         this.status = stat;
         this.data = data;
     }

     public void setStatus(String status){
         this.status = status;
     }
     public Long getWhoRequested(){return getId().getLeft();}
     public Long getWhoRecived(){return getId().getRight();}
     public LocalDateTime getdata(){return this.data;}

    public String getStatus() {
        return status;
    }
}
