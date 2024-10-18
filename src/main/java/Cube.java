import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.*;

public class Cube extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JLabel label = new JLabel("", JLabel.CENTER);

    String title;

    int state = 1;

    Map<Integer, String> map = new HashMap<>();

    List<String> listOfOLL = new ArrayList<>();
    List<String> listOfPLL = new ArrayList<>();
    List<String> listOfF2L = new ArrayList<>();

    {

        map.put(1, "OLL");
        map.put(2, "PLL");
        map.put(3, "F2L");

        File file;
        for (int i = 0; i < map.size(); i++) {
            file = new File(Objects.requireNonNull(Cube.class.getResource("")).getPath() + File.separator + map.get(i + 1));
            for (File f : Objects.requireNonNull(file.listFiles())) {
                if (f.isFile()) {
                    if (i == 0) {
                        listOfOLL.add(f.getAbsolutePath());
                    }
                    if (i == 1) {
                        listOfPLL.add(f.getAbsolutePath());
                    }
                    if (i == 2) {
                        listOfF2L.add(f.getAbsolutePath());
                    }
                }
            }
        }
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
            }
        });

        this.setTitle(title);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public JLabel insertIcon(int state) {
        ImageIcon ii = new ImageIcon(getAbsolutePath(state));
        label.setIcon(ii);
        return label;
    }

    public String getAbsolutePath(int state) {
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

        return name;
    }

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.init();
    }
}
