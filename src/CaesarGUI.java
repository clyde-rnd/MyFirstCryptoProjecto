import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;

public class CaesarGUI extends JFrame{
    private static int keyVal;
    private static Path inputPath;
    private static Path outputPath;

    CaesarGUI(){
        JFrame frameMainWindow = new JFrame("Caesar  Cipher");
        frameMainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMainWindow.setLocationRelativeTo(null);
        frameMainWindow.setResizable(true);
        frameMainWindow.setLayout(new GridLayout(5, 3, 1, 1));

        JLabel labelKey = new JLabel();
        JPanel panelLabelKey = new JPanel(new GridLayout(1, 1, 1, 1));
        panelLabelKey.add(labelKey);

        JButton start = new JButton("Start");
        JPanel panelStart = new JPanel(new GridLayout(1, 1, 1, 1));
        panelStart.add(start);

        JTextField inputKey = new JTextField("InputKey 1-75");
        JPanel panelInputKey = new JPanel(new GridLayout(1, 1, 1, 1));
        panelInputKey .add(inputKey);

        JButton addInputPath = new JButton("addInputFile");
        JPanel panelInputFile = new JPanel(new GridLayout(1, 1, 1, 1));
        panelInputFile.add(addInputPath);

        JButton addOutputPath = new JButton("addOutputFile");
        JPanel panelOutputFile = new JPanel(new GridLayout(1, 1, 1, 1));
        panelOutputFile.add(addOutputPath);

        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel panelRadioBattons = new JPanel(new GridLayout(0, 3, 1, 1));
        JRadioButton Encode = new JRadioButton("Encode", true);
        JRadioButton Decode = new JRadioButton("Decode");
        JRadioButton BrutForce = new JRadioButton("BrutForce");

        panelRadioBattons.add(Encode);
        panelRadioBattons.add(Decode);
        panelRadioBattons.add(BrutForce);

        buttonGroup.add(Encode);
        buttonGroup.add(Decode);
        buttonGroup.add(BrutForce);
       ActionListener radioBatt = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(Encode.isSelected()){
                   frameMainWindow.remove(panelLabelKey);
                   frameMainWindow.add(panelInputKey);
                   frameMainWindow.add(panelInputFile);
                   frameMainWindow.add(panelOutputFile);
                   frameMainWindow.add(start);
               }else if (Decode.isSelected()){
                   frameMainWindow.remove(panelLabelKey);
                   frameMainWindow.add(panelInputKey);
                   frameMainWindow.add(panelInputFile);
                   frameMainWindow.add(panelOutputFile);
                   frameMainWindow.add(start);
               } else if (BrutForce.isSelected()) {
                   frameMainWindow.remove(panelInputKey);
                   frameMainWindow.add(panelInputFile);
                   frameMainWindow.remove(panelOutputFile);
                   frameMainWindow.add(start);
               }
               frameMainWindow.pack();
               frameMainWindow.setVisible(true);
           }
       };
        Encode.addActionListener(radioBatt);
        Decode.addActionListener(radioBatt);
        BrutForce.addActionListener(radioBatt);
        addInputPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addInputPath.isSelected());
                JFileChooser fileChooserIn = new JFileChooser();
                int response = fileChooserIn.showOpenDialog(null);
                if(response==JFileChooser.APPROVE_OPTION){
                    inputPath = Path.of(fileChooserIn.getSelectedFile().getPath());
                    addInputPath.setText(String.valueOf(fileChooserIn.getSelectedFile())) ;
                }
                frameMainWindow.pack();
                frameMainWindow.setVisible(true);

            }
        });

        addOutputPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addOutputPath.isSelected());
                JFileChooser fileChooserOut = new JFileChooser();
                int response = fileChooserOut.showOpenDialog(null);
                if(response==JFileChooser.APPROVE_OPTION){
                    outputPath = Path.of(fileChooserOut.getSelectedFile().getPath());
                   addOutputPath.setText(String.valueOf(fileChooserOut.getSelectedFile())) ;
                }
                frameMainWindow.pack();
                frameMainWindow.setVisible(true);

            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Encode.isSelected()){
                    keyVal = Integer.parseInt(inputKey.getText());
                    CharCrypto newCrypt = new CharCrypto();
                    newCrypt.setKey(keyVal);
                   DecodeEncodeFile.encodeFile(inputPath,outputPath, newCrypt);
                } else if (Decode.isSelected()){
                    keyVal = Integer.parseInt(inputKey.getText());
                    CharCrypto newCrypt = new CharCrypto();
                    newCrypt.setKey(keyVal);
                    DecodeEncodeFile.decodeFile(inputPath,outputPath, newCrypt);

                } else if (BrutForce.isSelected()) {
                    outputPath = null;
                    CharCrypto newCrypt = new CharCrypto();
                    try {
                        outputPath = Validator.tempFile(inputPath);
                    } catch (IOException ee) {
                        throw new RuntimeException(ee);
                    }
                    int keyShowLabel = DecodeEncodeFile.bruteForce(inputPath, outputPath, newCrypt );
                    labelKey.setText("You key - " + (String.valueOf(keyShowLabel)));
                    frameMainWindow.add(panelLabelKey);

                }

                frameMainWindow.pack();
                frameMainWindow.setVisible(true);

            }
        });

        frameMainWindow.add(panelRadioBattons);
        if(Encode.isSelected()){
            frameMainWindow.add(panelInputKey);
            frameMainWindow.add(panelInputFile);
            frameMainWindow.add(panelOutputFile);
            frameMainWindow.add(start);
        }
        frameMainWindow.pack();
        frameMainWindow.setVisible(true);

    }


}
