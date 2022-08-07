package View;

import java.awt.*;
import javax.swing.*;

public class ResGui extends JFrame {
    JTextArea text = new JTextArea(25,70);
    JScrollPane s = new JScrollPane(text);
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    ImageIcon image = new ImageIcon("C:/Users/omega/Downloads/Imagine1.png");
    JLabel label = new JLabel();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();

    public ResGui() {
        panel.setBackground(new Color(120,225,225));
        panel.setBounds(0,0,800,700);
        panel1.setBackground(new Color(120,225,225));
        panel1.setBounds(800,0,200,700);
        text.setPreferredSize(new Dimension(3000,8000));

        s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.setSize(1050, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Coada si Clienti");
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(120,225,225));

        label.setIcon(image);
        label1.setIcon(image);
        label2.setIcon(image);
        this.add(panel);
        this.add(panel1);
        panel.add(s);
        panel1.add(label);
        panel1.add(label1);
        panel1.add(label2);
    }
    public void setRez(String y){
        text.setText(text.getText() + y + "\n");

    }
}
