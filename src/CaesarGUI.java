import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarGUI extends JFrame implements ActionListener {
    JRadioButton Encode;
    JRadioButton Decode;
    JRadioButton BrutForce;
    JFrame jFrame;
    JPanel panel2;
    CaesarGUI(){
        jFrame = new JFrame("Caesar  Cipher");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //jFrame.setSize(400,800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(true);
        jFrame.setLayout(new GridLayout(0, 1, 0, 4));
        JButton start = new JButton("Start");
        panel2 = new JPanel(new GridLayout(0, 1, 0, 5));
        panel2.add(start);

        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel panel1 = new JPanel(new GridLayout(0, 3, 0, 1));
        Encode = new JRadioButton("Encode");
        Decode = new JRadioButton("Decode");
        BrutForce = new JRadioButton("BrutForce");


        panel1.add(Encode);
        panel1.add(Decode);
        panel1.add(BrutForce);

        buttonGroup.add(Encode);
        buttonGroup.add(Decode);
        buttonGroup.add(BrutForce);

        Encode.addActionListener(this);
        Decode.addActionListener(this);
        BrutForce.addActionListener(this);

        jFrame.add(panel1);
        jFrame.pack();
        jFrame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Encode){
            jFrame.add(panel2);
            jFrame.pack();
            jFrame.setVisible(true);
            System.out.println("jjjj");
        }else {
            jFrame.remove(panel2);
            jFrame.pack();
            jFrame.setVisible(true);
        }


    }
}
