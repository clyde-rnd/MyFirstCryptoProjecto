import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarGUI extends JFrame{
    JRadioButton Encode;
    JRadioButton Decode;
    JRadioButton BrutForce;
    JFrame jFrame;
    JPanel panel2;
    CaesarGUI(){
        jFrame = new JFrame("Caesar  Cipher");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(true);
        jFrame.setLayout(new GridLayout(0, 1, 0, 4));
        JButton start = new JButton("Start");
        panel2 = new JPanel(new GridLayout(0, 1, 0, 5));
        panel2.add(start);
        JLabel lable = new JLabel("Input") ;
        JFileChooser fileChooser = new JFileChooser("/");
        JPanel panel3 = new JPanel(new GridLayout(0, 2, 0, 1));
        panel3.add(lable);
        panel3.add(fileChooser);


        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel panel1 = new JPanel(new GridLayout(0, 3, 0, 1));
        Encode = new JRadioButton("Encode", true);
        Decode = new JRadioButton("Decode");
        BrutForce = new JRadioButton("BrutForce");




        panel1.add(Encode);
        panel1.add(Decode);
        panel1.add(BrutForce);

        buttonGroup.add(Encode);
        buttonGroup.add(Decode);
        buttonGroup.add(BrutForce);
       ActionListener radioBatt = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(Encode.isSelected()){
                   jFrame.add(panel2);
               }else if (Decode.isSelected()){
                   jFrame.add(panel2);
               } else if (BrutForce.isSelected()) {
                   jFrame.remove(panel2);
               }
               jFrame.pack();
               jFrame.setVisible(true);
           }
       };
        Encode.addActionListener(radioBatt);
        Decode.addActionListener(radioBatt);
        BrutForce.addActionListener(radioBatt);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (start.isSelected());
                System.out.println("ssss");
            }
        });


        jFrame.add(panel1);
        if(Encode.isSelected()){
            jFrame.add(panel2);
        }
        jFrame.add(panel3);
        jFrame.pack();
        jFrame.setVisible(true);

    }


}
