package advanced.java.threads;

public class Data2 {

    private String message;

    public void send(String message){
        this.message = message;
    }

    public String receive(){
        return message;
    }
}
