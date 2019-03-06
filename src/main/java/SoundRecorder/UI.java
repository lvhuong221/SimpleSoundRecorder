package SoundRecorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame{
    private SoundRecorder soundRecorder;
    // JPanel panel;
    private JLabel label;
    private JButton startBtn, stopBtn, folderBtn;
    private boolean eFlag = false;
    Thread starter;
    //Constructor
    public UI(String frameText){
        //Initialize soundRecorder
        //Initialize JFrame
        JFrame frame = this;
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setName(frameText);
        frame.setResizable(false);
        frame.setSize(400,100);

        frame.setLayout(null);
        Border emptyBorder = BorderFactory.createEmptyBorder();

        //Setup start buttons
        startBtn = new JButton(new ImageIcon("Icons/Start_button.png"));
        startBtn.setBounds(20,20,40,40);
        startBtn.setVisible(true);
        startBtn.setBorder(null);
        //Button action
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBtnPressed();
            }
        });
        //Set up pause button
        //Set up stop buttons
        stopBtn = new JButton(new ImageIcon("Icons/Stop_button.png"));
        stopBtn.setBounds(60,20,40,40);
        stopBtn.setVisible(false);
        stopBtn.setBorder(null);
        //Button action
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopBtnPressed();
            }
        });

        //Set co-ordinate for Folder button
        // TODO: add action to choose save folder
        folderBtn = new JButton(new ImageIcon(("Icons/Folder.png")));
        folderBtn.setBounds(100,20,40,40);
        folderBtn.setVisible(true);
        folderBtn.setBorder(null);

        //Put elements on JFrame
        frame.add(startBtn);
        frame.add(stopBtn);
        frame.add(folderBtn);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void  startBtnPressed() {
        soundRecorder = new SoundRecorder();
        startBtn.setVisible(false);
        stopBtn.setVisible(true);
        Thread starter = new Thread(new Runnable() {
            @Override
            public void run() {
                soundRecorder.start();
            }
        });
        starter.start();
    }
    public void stopBtnPressed(){
        stopBtn.setVisible(false);
        startBtn.setVisible(true);
        soundRecorder.finish();
    }
    public static void main(String[] args) {
        UI ui = new UI("Recortder");

    }
}
