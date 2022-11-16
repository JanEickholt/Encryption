package src;

import java.util.ArrayList;

public class Message {
    String time;
    String name;
    String message;
    String message_type;
    String message_status;
    static ArrayList<Message> messages = new ArrayList<Message>();

    public void addMessage() {
        Message message = new Message();
        messages.add(message);
    }
}
