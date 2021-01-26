import javax.swing.*;
import java.awt.*;

public class BasicClass {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFild gameFild = new GameFild();
                gameFild.setSize(new Dimension(500,575));
                gameFild.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFild.setVisible(true);
            }
        });
    }
}


