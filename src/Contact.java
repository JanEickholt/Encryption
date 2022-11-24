package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Contact {
    int y_level;
    String name;
    String profile_picture_url;
    ImageIcon profile_picture = null;
    static ArrayList<Integer> y_levels = new ArrayList<>();
    static ArrayList<Contact> contacts = new ArrayList <> ();
    static ArrayList<Contact> sorted_contacts;

    public void createContact(int y_level, String name, String profile_picture_url) throws IOException {
        Contact contact = new Contact();
        contact.name = name;
        contact.y_level = y_level;
        contact.profile_picture_url = profile_picture_url;

        if(profile_picture_url != null) {
            System.out.println(profile_picture_url);
            contact.profile_picture = new ImageIcon(new URL(profile_picture_url));
            URL url = new URL(profile_picture_url);
            BufferedImage c = resizeImage(ImageIO.read(url), 50, 50);
            contact.profile_picture = new ImageIcon(c);
        }
        else {
            contact.profile_picture = new ImageIcon("src/images/default_profile_picture.png");
        }

        contacts.add(contact);
        sort_contacts();
    }

    public static boolean in_array(int filter, ArrayList<Integer> array){
        for(int element: array){
            if(element == filter){
                return true;
            }
        }
        return false;
    }

    public static void sort_contacts() {
        sorted_contacts = new ArrayList<>();
        y_levels = new ArrayList<>();
        for (Contact contact : contacts) {
            if (sorted_contacts.size() == 0) {
                sorted_contacts.add(contact);
                y_levels.add(contact.y_level);
            } else {
                for (int i = 0; i < sorted_contacts.size(); i++) {
                    System.out.println(contact.y_level);
                    if(in_array(contact.y_level, y_levels)){
                        System.out.println(y_levels);
                        break;
                    }
                    if (contact.y_level < sorted_contacts.get(i).y_level) {
                        sorted_contacts.add(i, contact);
                        y_levels.add(contact.y_level);
                        break;
                    } else if (i == sorted_contacts.size() - 1) {
                        sorted_contacts.add(contact);
                        y_levels.add(contact.y_level);
                        break;
                    }
                }
            }
        }
    }

    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}
