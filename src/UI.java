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
        top_label.setText("Illegal Whatsapp thing");
        top_label.setBounds((Whatsapp.width - 500) / 2, 15, 500, 100);
        top_label.setFont(new Font("Roboto", Font.PLAIN, 30));
        top_label.setHorizontalAlignment(JLabel.CENTER);
        content_pane.add(top_label);

        ImageIcon loading_icon = new ImageIcon("src/images/loading.gif");
        JLabel loading_label = new JLabel(loading_icon);
        loading_label.setBounds(0, -50, Whatsapp.width, Whatsapp.height);
        content_pane.add(loading_label);
    }

    void create_contact_button(JButton btn , ArrayList<Contact> contacts, int contact_index) {
        btn.setBounds(120, 51 * contact_index + 115, 200, 50);
        btn.setFont(new Font("Roboto", Font.PLAIN, 15));
        btn.setText(contacts.get(contact_index).name);
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btn.addActionListener(arg0 -> changeToChatScreen(contacts, contact_index));
        content_pane.add(btn);
    }
    void changeToContactsScreen(ArrayList<Contact> contacts) {
        content_pane.removeAll();
        content_pane.repaint();
        content_pane.revalidate();

        for(int contact_index = 0; contact_index < contacts.size(); contact_index++) {
            System.out.println("starting new contact");
            create_contact_button(new JButton(), contacts, contact_index);

            JLabel profile_picture_label;
            profile_picture_label = new JLabel(contacts.get(contact_index).profile_picture);
            profile_picture_label.setBounds(70, 51 * contact_index + 115, 50, 50);
            profile_picture_label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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

    void changeToMainScreen(byte[] bytes) {
        content_pane.removeAll();
        content_pane.repaint();
        content_pane.revalidate();

        JLabel top_label = new JLabel();
        top_label.setText("Illegal Whatsapp thing");
        top_label.setBounds((Whatsapp.width - 500) / 2, 25, 500, 100);
        top_label.setFont(new Font("Roboto", Font.PLAIN, 30));
        top_label.setHorizontalAlignment(JLabel.CENTER);
        content_pane.add(top_label);

        ImageIcon image = new ImageIcon(bytes);
        JLabel image_label = new JLabel(image);
        image_label.setBounds((Whatsapp.width - 500) / 2, 150, 500, 500);
        content_pane.add(image_label);

        content_pane.repaint();
        content_pane.revalidate();
    }

    void changeToChatScreen(ArrayList<Contact> contacts, int contact_index){
        System.out.println("changing to chat screen");
        System.out.println(contacts.get(contact_index).name);
        System.out.println(contact_index);
    }
}
