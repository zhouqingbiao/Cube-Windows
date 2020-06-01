import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class Cube extends JFrame {
    // JLabel在此定义
    // 须重复使用
    JLabel label = new JLabel("", JLabel.CENTER);

    // 窗口标题
    String title;

    // 根目录
    String root;

    // 二级CFOP目录
    String f2l;
    String oll;
    String pll;

    // CFOP状态
    String cfop;

    // CFOP图片名称
    String[] f2ls;
    String[] olls;
    String[] plls;

    {
        // 获取Properties数据
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("Cube.properties"));
            // 窗口标题
            title = properties.getProperty("title");

            // 根目录
            root = properties.getProperty("root");

            // 二级CFOP目录
            f2l = properties.getProperty("F2L");
            oll = properties.getProperty("OLL");
            pll = properties.getProperty("PLL");

            // CFOP图片名称
            f2ls = properties.getProperty("F2Ls").split("#");
            olls = properties.getProperty("OLLs").split("#");
            plls = properties.getProperty("PLLs").split("#");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

        // 设置窗口标题
        this.setTitle(title);
        // 设置窗口大小
        this.setSize(400, 400);
        // JFrame居中显示
        this.setLocationRelativeTo(null);
        // 能够点击关闭窗口
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 窗口可见
        this.setVisible(true);

    }

    /**
     * 初始化JLabel，即随机切换OLL/PLL。
     */
    public JLabel initJLabel(String cfop) {
        ImageIcon ii = null;
        try {
            ii = new ImageIcon(ImageIO.read(path(cfop)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 放大图片，但是放大后失真，不知如何处理。
        // oll放大不失真，pll放大失真。
        if (oll.equals(cfop) || null == cfop) {
            // 断言
            assert ii != null;
            ii.setImage(ii.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        }
        // 设置图片
        label.setIcon(ii);
        return label;
    }

    /**
     * 获取OLL/PLL路径。
     */
    public InputStream path(String cfop) {
        // 随机
        Random random = new Random();

        // 组装路径
        String name;
        if (f2l.equals(cfop)) {
            name = f2l + "/" + f2ls[(random.nextInt(f2ls.length))];
        } else if (oll.equals(cfop)) {
            name = oll + "/" + olls[(random.nextInt(olls.length))];
        } else if (pll.equals(cfop)) {
            name = pll + "/" + plls[(random.nextInt(plls.length))];
        } else {
            name = oll + "/" + olls[(random.nextInt(olls.length))];
        }
        name = root + "/" + name;

        // getResourceAsStream可以获取流
        return this.getClass().getResourceAsStream(name);
    }

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.init();
    }
}
