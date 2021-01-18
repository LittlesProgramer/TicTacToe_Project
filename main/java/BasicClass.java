import javax.swing.*;
import java.awt.*;

public class Basic_Class {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFild gameFild = new GameFild();
                gameFild.setSize(new Dimension(500,500));
                gameFild.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFild.setVisible(true);
            }
        });
    }
}


