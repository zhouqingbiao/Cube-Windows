import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.*;

public class Cube extends JFrame {
    private static final long serialVersionUID = 1L;

    JLabel label = new JLabel("", JLabel.CENTER);

    String title;

    int state = 1;

    List<String> unmodifiableListOfOLL = Collections.unmodifiableList(Arrays.asList("OLL/O1.gif", "OLL/O10.gif", "OLL/O11.gif", "OLL/O12.gif", "OLL/O13.gif", "OLL/O14.gif", "OLL/O15.gif", "OLL/O16.gif", "OLL/O17.gif", "OLL/O18.gif", "OLL/O19.gif", "OLL/O2.gif", "OLL/O20.gif", "OLL/O21.gif", "OLL/O22.gif", "OLL/O23.gif", "OLL/O24.gif", "OLL/O25.gif", "OLL/O28.gif", "OLL/O29.gif", "OLL/O3.gif", "OLL/O30.gif", "OLL/O31.gif", "OLL/O32.gif", "OLL/O33.gif", "OLL/O34.gif", "OLL/O35.gif", "OLL/O36.gif", "OLL/O37.gif", "OLL/O38.gif", "OLL/O39.gif", "OLL/O4.gif", "OLL/O40.gif", "OLL/O41.gif", "OLL/O42.gif", "OLL/O43.gif", "OLL/O44.gif", "OLL/O45.gif", "OLL/O46.gif", "OLL/O47.gif", "OLL/O48.gif", "OLL/O49.gif", "OLL/O5.gif", "OLL/O50.gif", "OLL/O51.gif", "OLL/O52.gif", "OLL/O53.gif", "OLL/O54.gif", "OLL/O55.gif", "OLL/O56.gif", "OLL/O57.gif", "OLL/O6.gif", "OLL/O7.gif", "OLL/O8.gif", "OLL/O9.gif", "OLL/oll1.gif", "OLL/oll2.gif"));
    List<String> listOfOLL = new ArrayList<>(unmodifiableListOfOLL);

    List<String> unmodifiableListOfPLL = Collections.unmodifiableList(Arrays.asList("PLL/A.gif", "PLL/A1.gif", "PLL/E.gif", "PLL/F.gif", "PLL/G.gif", "PLL/G1.gif", "PLL/G2.gif", "PLL/G3.gif", "PLL/H.gif", "PLL/J.gif", "PLL/J1.gif", "PLL/N.gif", "PLL/N1.gif", "PLL/R.gif", "PLL/R1.gif", "PLL/T.gif", "PLL/U.gif", "PLL/U1.gif", "PLL/V.gif", "PLL/Y.gif", "PLL/Z.gif"));
    List<String> listOfPLL = new ArrayList<>(unmodifiableListOfPLL);

    List<String> unmodifiableListOfF2L = Collections.unmodifiableList(Arrays.asList("F2L/f2l1.gif", "F2L/f2l10.gif", "F2L/f2l11.gif", "F2L/f2l12.gif", "F2L/f2l13.gif", "F2L/f2l14.gif", "F2L/f2l15.gif", "F2L/f2l16.gif", "F2L/f2l17.gif", "F2L/f2l18.gif", "F2L/f2l19.gif", "F2L/f2l2.gif", "F2L/f2l20.gif", "F2L/f2l21.gif", "F2L/f2l22.gif", "F2L/f2l23.gif", "F2L/f2l24.gif", "F2L/f2l25.gif", "F2L/f2l26.gif", "F2L/f2l27.gif", "F2L/f2l28.gif", "F2L/f2l29.gif", "F2L/f2l2_1.gif", "F2L/f2l2_2.gif", "F2L/f2l3.gif", "F2L/f2l30.gif", "F2L/f2l31.gif", "F2L/f2l32.gif", "F2L/f2l33.gif", "F2L/f2l34.gif", "F2L/f2l35.gif", "F2L/f2l35_1.gif", "F2L/f2l36.gif", "F2L/f2l36_1.gif", "F2L/f2l37.gif", "F2L/f2l38.gif", "F2L/f2l39.gif", "F2L/f2l3_1.gif", "F2L/f2l3_2.gif", "F2L/f2l4.gif", "F2L/f2l40.gif", "F2L/f2l41.gif", "F2L/f2l5.gif", "F2L/f2l6.gif", "F2L/f2l7.gif", "F2L/f2l8.gif", "F2L/f2l9.gif"));
    List<String> listOfF2L = new ArrayList<>(unmodifiableListOfF2L);

    public static void main(String[] args) {
        new Cube().init();
    }

    public void init() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(insertIcon(state), BorderLayout.CENTER);

        this.getContentPane().add(panel);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    insertIcon(state);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    state = 1;
                    insertIcon(state);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    state = 2;
                    insertIcon(state);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    state = 3;
                    insertIcon(state);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    listOfOLL = new ArrayList<>(unmodifiableListOfOLL);
                    listOfPLL = new ArrayList<>(unmodifiableListOfPLL);
                    listOfF2L = new ArrayList<>(unmodifiableListOfF2L);
                    insertIcon(state);
                }
            }
        });

        this.setTitle(title);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JLabel insertIcon(int state) {
        ImageIcon ii = null;
        try {
            ii = new ImageIcon(ImageIO.read(getInputStream(state)));
        } catch (IOException | NullPointerException | IllegalArgumentException ignored) {

        }
        label.setIcon(ii);
        return label;
    }

    public InputStream getInputStream(int state) {
        Random random = new Random();

        String name = "";

        int i;

        if (state == 1) {
            if (!listOfOLL.isEmpty()) {
                i = random.nextInt(listOfOLL.size());
                name = listOfOLL.get(i);
                listOfOLL.remove(i);
            }
        }
        if (state == 2) {
            if (!listOfPLL.isEmpty()) {
                i = random.nextInt(listOfPLL.size());
                name = listOfPLL.get(i);
                listOfPLL.remove(i);
            }
        }
        if (state == 3) {
            if (!listOfF2L.isEmpty()) {
                i = random.nextInt(listOfF2L.size());
                name = listOfF2L.get(i);
                listOfF2L.remove(i);
            }
        }

        return this.getClass().getResourceAsStream(name);
    }
}
