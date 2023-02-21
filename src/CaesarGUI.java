import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaesarGUI extends JFrame{
    private static int keyVal;
    private static Path inputPath;
    private static Path outputPath;

    CaesarGUI(){
        /**
         * Create main window
         */
        JFrame frameMainWindow = new JFrame("Caesar  Cipher");
        frameMainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMainWindow.setLocationRelativeTo(null);
        frameMainWindow.setResizable(true);
        frameMainWindow.setLayout(new GridLayout(5, 3, 1, 1));

        /**
         * Create Button and label
         */
        JLabel labelKey = new JLabel();
        JPanel panelLabelKey = new JPanel(new GridLayout(1, 1, 1, 1));
        panelLabelKey.add(labelKey);

        JButton start = new JButton("Start");
        JPanel panelStart = new JPanel(new GridLayout(1, 1, 1, 1));
        panelStart.add(start);

        JTextField inputKey = new JTextField("InputKey 1-85");
        JPanel panelInputKey = new JPanel(new GridLayout(1, 1, 1, 1));
        panelInputKey .add(inputKey);

        JButton addInputPath = new JButton("add.. InputFile");
        JPanel panelInputFile = new JPanel(new GridLayout(1, 1, 1, 1));
        panelInputFile.add(addInputPath);

        JButton addOutputPath = new JButton("add.. OutputFile");
        JPanel panelOutputFile = new JPanel(new GridLayout(1, 1, 1, 1));
        panelOutputFile.add(addOutputPath);

        /**
         * Create Radio Button and add button on panel
         */
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel panelRadioButtons = new JPanel(new GridLayout(0, 3, 1, 1));
        JRadioButton encode = new JRadioButton("Encode", true);
        JRadioButton decode = new JRadioButton("Decode");
        JRadioButton brutForce = new JRadioButton("BrutForce");

        panelRadioButtons.add(encode);
        panelRadioButtons.add(decode);
        panelRadioButtons.add(brutForce);

        buttonGroup.add(encode);
        buttonGroup.add(decode);
        buttonGroup.add(brutForce);

        /**
         * Listener Radio Button
         */
       ActionListener radioButton = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(encode.isSelected()){
                   frameMainWindow.remove(panelLabelKey);
                   frameMainWindow.add(panelInputKey);
                   frameMainWindow.add(panelInputFile);
                   frameMainWindow.add(panelOutputFile);
                   frameMainWindow.add(start);
               }else if (decode.isSelected()){
                   frameMainWindow.remove(panelLabelKey);
                   frameMainWindow.add(panelInputKey);
                   frameMainWindow.add(panelInputFile);
                   frameMainWindow.add(panelOutputFile);
                   frameMainWindow.add(start);
               } else if (brutForce.isSelected()) {
                   frameMainWindow.remove(panelInputKey);
                   frameMainWindow.add(panelInputFile);
                   frameMainWindow.remove(panelOutputFile);
                   frameMainWindow.add(start);
               }
               frameMainWindow.pack();
               frameMainWindow.setVisible(true);
           }
       };

        encode.addActionListener(radioButton);
        decode.addActionListener(radioButton);
        brutForce.addActionListener(radioButton);

        /**
         * Listener Input Path Button
         */
        addInputPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addInputPath.isSelected());
                JFileChooser fileChooserIn = new JFileChooser();
                int response = fileChooserIn.showOpenDialog(null);
                if(response==JFileChooser.APPROVE_OPTION){
                    inputPath = Path.of(fileChooserIn.getSelectedFile().getPath());
                    addInputPath.setText(String.valueOf(fileChooserIn.getSelectedFile()));
                }
                frameMainWindow.pack();
                frameMainWindow.setVisible(true);
            }
        });
/**
 * Listener Output Path Button
 */
        addOutputPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addOutputPath.isSelected());
                JFileChooser fileChooserOut = new JFileChooser();
                int response = fileChooserOut.showOpenDialog(null);
                if(response==JFileChooser.APPROVE_OPTION){
                    outputPath = Path.of(fileChooserOut.getSelectedFile().getPath());
                   addOutputPath.setText(String.valueOf(fileChooserOut.getSelectedFile()));
                }
                frameMainWindow.pack();
                frameMainWindow.setVisible(true);
            }
        });
/**
 * Listener Start Button
 */
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (encode.isSelected()) {
                    if (Validator.keyReaderGUI(inputKey.getText())) {
                        keyVal = Integer.parseInt(inputKey.getText());
                        CharCrypto newCrypt = new CharCrypto();
                        newCrypt.setKey(keyVal);
                        if (Validator.validatorPathGUI(inputPath) && Validator.validatorPathGUI(outputPath)) {
                            DecodeEncodeFile.encodeFile(inputPath, outputPath, newCrypt);
                            inputKey.setText("DONE!!!! You KEY " + newCrypt.getKey());
                        } else {
                            inputKey.setText("Input Correct Path");
                        }

                    } else {
                        inputKey.setText("Not Valid KEY");
                    }

                } else if (decode.isSelected()) {
                    if (Validator.keyReaderGUI(inputKey.getText())) {
                        keyVal = Integer.parseInt(inputKey.getText());
                        CharCrypto newCrypt = new CharCrypto();
                        newCrypt.setKey(keyVal);
                        if (Validator.validatorPathGUI(inputPath) && Validator.validatorPathGUI(outputPath)) {
                            DecodeEncodeFile.decodeFile(inputPath, outputPath, newCrypt);
                            inputKey.setText("DONE!!!!");
                        } else {
                            inputKey.setText("Input Correct Path");
                        }

                    } else {
                        inputKey.setText("Not Valid KEY");
                    }

                } else if (brutForce.isSelected()) {
                    outputPath = null;
                    if (Validator.validatorPathGUI(inputPath)) {
                        CharCrypto newCrypt = new CharCrypto();
                        try {
                            outputPath = Validator.tempFile(inputPath);
                        } catch (IOException ee) {
                            throw new RuntimeException(ee);
                        }
                        int keyShowLabel = DecodeEncodeFile.bruteForce(inputPath, outputPath, newCrypt);
                        System.out.println(keyShowLabel);
                        labelKey.setText("You key - " + (String.valueOf(keyShowLabel)));
                        frameMainWindow.add(panelLabelKey);
                    } else {
                        addInputPath.setText("Not valid InPath");
                    }

                    frameMainWindow.pack();
                    frameMainWindow.setVisible(true);

                }
            }
        });
        /**
         * add button and label on Main Window
         */
        frameMainWindow.add(panelRadioButtons);
        if(encode.isSelected()){
            frameMainWindow.add(panelInputKey);
            frameMainWindow.add(panelInputFile);
            frameMainWindow.add(panelOutputFile);
            frameMainWindow.add(start);
        }
        frameMainWindow.pack();
        frameMainWindow.setVisible(true);

    }


}
