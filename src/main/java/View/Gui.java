package View;
import Model.SimulationManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JFrame implements ActionListener {

    public static Thread threadSimulationManager;
    JLabel label_cl = new JLabel("Numarul clientilor:        ");
    JLabel label_qu = new JLabel("          Numarul cozilor:  ");
    JLabel label_SI = new JLabel("Intervalul Simularii:");
    JLabel label_MinA = new JLabel("Ora minima de sosire:   ");
    JLabel label_MaxA = new JLabel("Ora maxima de sosire:  ");
    JLabel label_MinS = new JLabel("Ora minima de servire: ");
    JLabel label_MaxS = new JLabel("Ora maxima de servire:");
    JTextField text_cl = new JTextField();
    JTextField text_qu = new JTextField();
    JTextField text_SI = new JTextField();
    JTextField text_MinA = new JTextField();
    JTextField text_MaxA = new JTextField();
    JTextField text_MinS = new JTextField();
    JTextField text_MaxS = new JTextField();
    JButton button = new JButton("GO");
    JPanel panel = new JPanel();
    public static int x;
    public static int x1;
    public static int x2;
    public static int x3;
    public static int x4;
    public static int x5;
    public static int x6;

    public Gui() {
        panel.setBackground(new Color(220, 200, 255));
        panel.setBounds(0, 0, 600, 120);

        text_cl.setPreferredSize(new Dimension(120, 22));
        text_qu.setPreferredSize(new Dimension(120, 22));
        text_SI.setPreferredSize(new Dimension(120, 22));
        text_MinA.setPreferredSize(new Dimension(120, 22));
        text_MaxA.setPreferredSize(new Dimension(120, 22));
        text_MinS.setPreferredSize(new Dimension(120, 22));
        text_MaxS.setPreferredSize(new Dimension(120, 22));

        button.setFocusable(false);
        button.addActionListener(this);

        label_cl.setForeground(Color.gray);
        label_MaxA.setForeground(Color.gray);
        label_MaxS.setForeground(Color.gray);
        label_MinA.setForeground(Color.gray);
        label_MinS.setForeground(Color.gray);
        label_qu.setForeground(Color.gray);
        label_SI.setForeground(Color.gray);

        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setTitle("Coada si Clienti");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(220, 200, 255));

        this.add(panel);
        panel.add(label_cl);
        panel.add(text_cl);
        panel.add(label_qu);
        panel.add(text_qu);
        panel.add(label_MinA);
        panel.add(text_MinA);
        panel.add(label_MaxA);
        panel.add(text_MaxA);
        panel.add(label_MinS);
        panel.add(text_MinS);
        panel.add(label_MaxS);
        panel.add(text_MaxS);
        panel.add(label_SI);
        panel.add(text_SI);
        panel.add(button);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            x = Integer.parseInt(text_cl.getText());
            x1 = Integer.parseInt(text_qu.getText());
            x2 = Integer.parseInt(text_MinA.getText());
            x3 = Integer.parseInt(text_MaxA.getText());
            x4 = Integer.parseInt(text_MinS.getText());
            x5 = Integer.parseInt(text_MaxS.getText());
            x6 = Integer.parseInt(text_SI.getText());
            ResGui g = new ResGui();
            SimulationManager sm = new SimulationManager(g);
            threadSimulationManager.start();
        }

      }
}
