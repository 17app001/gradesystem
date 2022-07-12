package tw.com.jerrycode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GradeSystem extends JFrame implements ActionListener {

    private JTextField nameText;
    private JTextField chineseText;
    private JTextField mathText;
    private JTextField englishText;
    private JButton addBtn;
    private JTextArea outputAtea;
    private JButton saveBtn;

    private Font font1;
    private Font font2;
    private Font font3;

    private final int WIDTH = 600;
    private final int HEIGHT = 600;

    GradeSystem() {

        font1 = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        font2 = new Font(Font.MONOSPACED, Font.PLAIN, 24);
        font3 = new Font(Font.MONOSPACED, Font.PLAIN, 28);

        // 畫面置中
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize.width);
        System.out.println(screenSize.height);

        int x = (screenSize.width - WIDTH) / 2;
        int y = (screenSize.height - HEIGHT) / 2;

        setBounds(x, y, WIDTH, HEIGHT);
        setTitle("學生成績輸入系統");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JFrame布局樣式
        setLayout(null);
        // 左邊樣式布局
        initLeftPanel();
    }

    void initLeftPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, (int) (WIDTH * 0.4), HEIGHT - 60);
        // panel.setBackground(Color.ORANGE);
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                "輸入"));

        add(panel);

        // 增加Label
        panel.add(new JLabel("姓名"));
        nameText = getTextField("請輸入姓名", 14, font2);
        panel.add(nameText);

        panel.add(new JLabel("國文"));
        chineseText = getTextField("請輸入國文", 14, font2);
        panel.add(chineseText);

        panel.add(new JLabel("英文"));
        englishText = getTextField("請輸入英文", 14, font2);
        panel.add(englishText);

        panel.add(new JLabel("數學"));
        mathText = getTextField("請輸入數學", 14, font2);
        panel.add(mathText);

    }

    private JTextField getTextField(String toolTipText, int columns, Font font) {
        JTextField field = new JTextField();
        field.setFont(font);
        field.setColumns(columns);
        field.setToolTipText(toolTipText);
        field.setHorizontalAlignment(JTextField.CENTER);

        return field;
    }

    public void run(boolean visible) {
        setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
