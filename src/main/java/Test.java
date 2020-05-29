import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

public class Test extends JFrame {
    String title = "Cube";
    String cfop;
    String f2l = "F2L";
    String oll = "OLL";
    String pll = "PLL";
    String resource = "RUBIK" + File.separator;

    public void init() {
        // 使用BorderLayout方便JLabel水平垂直居中
        JPanel panel = new JPanel(new BorderLayout());

        // 居中添加JLabel
        panel.add(initJLabel(cfop), BorderLayout.CENTER);

        // 添加JPanel
        this.getContentPane().add(panel);

        // 添加键盘事件（空格键随机切换OLL/PLL，左键OLL，右键PLL。）
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // 空格键
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    initJLabel(cfop);
                }
                // 左键
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    cfop = oll;
                    initJLabel(cfop);
                }
                // 右键
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    cfop = pll;
                    initJLabel(cfop);
                }
            }
        });

        this.setTitle(title);
        this.setSize(400, 400);
        // JFrame居中显示
        this.setLocationRelativeTo(null);
        // 能够点击关闭窗口
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 窗口可见
        this.setVisible(true);

    }

    JLabel label = new JLabel("", JLabel.CENTER);

    /**
     * 初始化JLabel，即随机切换OLL/PLL。
     */
    public JLabel initJLabel(String cfop) {
        ImageIcon ii = new ImageIcon(path(cfop));
        // 放大图片，但是放大后失真，不知如何处理。
        // oll放大不失真，pll放大失真。
        if (oll.equals(cfop) || null == cfop) {
            ii.setImage(ii.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        }
        label.setIcon(ii);
        return label;
    }

    /**
     * 获取OLL/PLL路径。
     */
    public String path(String cfop) {
        // 默认使用OLL
        if (cfop == null) {
            cfop = oll;
        }
        // 有可能NullPointerException，未作处理。
        File file = new File(resource + cfop);
        // File file = new File(this.getClass().getClassLoader().getResource(resource + cfop).getPath());
        File[] files = file.listFiles();
        Random random = new Random();
        // 有可能NullPointerException，未作处理。
        return files[random.nextInt(files.length)].getPath();
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.init();
    }
}
