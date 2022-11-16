package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class UI {
    static JPanel content_pane = new JPanel();
    public UI() {
        content_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        content_pane.setLayout(null);

        JLabel top_label = new JLabel();
        // der Text über dem Start Button wird erstellt
        top_label.setText("Illegal Whatsapp thing");
        top_label.setBounds((Whatsapp.width - 500) / 2, 25, 500, 100);
        top_label.setFont(new Font("Roboto", Font.PLAIN, 30));
        top_label.setHorizontalAlignment(JLabel.CENTER);
        content_pane.add(top_label);


        // loading icon
        ImageIcon loading_icon = new ImageIcon("src/images/loading.gif");
        JLabel loading_label = new JLabel(loading_icon);
        loading_label.setBounds(0, -50, Whatsapp.width, Whatsapp.height);
        content_pane.add(loading_label);
    }

    public static void changeToContactsScreen(ArrayList<Contact> contacts) {
        content_pane.removeAll();
        content_pane.repaint();
        content_pane.revalidate();

        for(int i = 0; i < contacts.size(); i++) {
            System.out.println("starting new contact");
            JButton contact_button = new JButton();
            contact_button.setBounds(120, 51 * i + 125, 200, 50);
            contact_button.setFont(new Font("Roboto", Font.PLAIN, 15));
            contact_button.setText(contacts.get(i).name);
            //contact_button.addActionListener(arg0 -> changeToChatScreen(contact));
            content_pane.add(contact_button);

            JLabel profile_picture_label;
            profile_picture_label = new JLabel(contacts.get(i).profile_picture);
            profile_picture_label.setBounds(70, 51 * i + 125, 50, 50);
            content_pane.add(profile_picture_label);
        }

        JLabel top_label = new JLabel();
        // der Text über dem Start Button wird erstellt
        top_label.setText("Contacts");
        top_label.setBounds(-55, 25, 500, 100);
        top_label.setFont(new Font("Roboto", Font.PLAIN, 30));
        top_label.setHorizontalAlignment(JLabel.CENTER);
        content_pane.add(top_label);

        content_pane.repaint();
        content_pane.revalidate();
    }

    static void changeToMainScreen(byte[] bytes) {
        content_pane.removeAll();
        content_pane.repaint();
        content_pane.revalidate();

        JLabel top_label = new JLabel();
        // der Text über dem Start Button wird erstellt
        top_label.setText("Illegal Whatsapp thing");
        top_label.setBounds((Whatsapp.width - 500) / 2, 25, 500, 100);
        top_label.setFont(new Font("Roboto", Font.PLAIN, 30));
        top_label.setHorizontalAlignment(JLabel.CENTER);
        content_pane.add(top_label);

        // show image
        ImageIcon image = new ImageIcon(bytes);
        JLabel image_label = new JLabel(image);
        image_label.setBounds((Whatsapp.width - 500) / 2, 150, 500, 500);
        content_pane.add(image_label);

        content_pane.repaint();
        content_pane.revalidate();
    }
}
