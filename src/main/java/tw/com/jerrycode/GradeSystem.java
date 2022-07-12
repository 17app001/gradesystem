package tw.com.jerrycode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GradeSystem extends JFrame implements ActionListener {

    private JTextField nameText;
    private JTextField chineseText;
    private JTextField mathText;
    private JTextField englishText;
    private JButton addBtn;
    private JTextArea outputArea;
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton clearBtn;
    private JCheckBox appendCbx;

    private Font font1;
    private Font font2;
    private Font font3;
    private Font font4;

    private final int WIDTH = 600;
    private final int HEIGHT = 550;

    private String fileName;

    GradeSystem() {
        fileName = "student.csv";
        font1 = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        font2 = new Font(Font.MONOSPACED, Font.PLAIN, 24);
        font3 = new Font(Font.MONOSPACED, Font.PLAIN, 28);
        font4 = new Font(Font.MONOSPACED, Font.BOLD, 20);

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
        // 右邊樣式布局
        initRightPanel();
    }

    private void initLeftPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, (int) (WIDTH * 0.4), HEIGHT - 60);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                "輸入"));

        add(panel);

        // 增加Label
        panel.add(getLabel("姓名", font4));
        nameText = getTextField("請輸入姓名", 14, font2);
        nameText.setText("Jerry");
        panel.add(nameText);

        panel.add(getLabel("國文", font4));
        chineseText = getTextField("請輸入國文", 14, font2);
        chineseText.setText("99.9");
        panel.add(chineseText);

        panel.add(getLabel("英文", font4));
        englishText = getTextField("請輸入英文", 14, font2);
        englishText.setText("69.9");
        panel.add(englishText);

        panel.add(getLabel("數學", font4));
        mathText = getTextField("請輸入數學", 14, font2);
        mathText.setText("100");
        panel.add(mathText);

        addBtn = new JButton("增加");
        addBtn.setFont(font3);
        addBtn.addActionListener(this);
        panel.add(addBtn);
    }

    private void initRightPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(10 + (int) (WIDTH * 0.4), 10, (int) (WIDTH * 0.55), HEIGHT - 120);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                "資料"));

        add(panel);

        outputArea = new JTextArea();
        outputArea.setBounds(10, 20, (int) (WIDTH * 0.52), HEIGHT - 150);
        outputArea.setFont(font4);
        panel.add(outputArea);

        panel = new JPanel();
        panel.setBounds(10 + (int) (WIDTH * 0.4), HEIGHT - 100, (int) (WIDTH * 0.55), 80);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(panel);

        saveBtn = new JButton("儲存");
        saveBtn.setFont(font2);
        saveBtn.addActionListener(this);
        panel.add(saveBtn);

        loadBtn = new JButton("讀取");
        loadBtn.setFont(font2);
        loadBtn.addActionListener(this);
        panel.add(loadBtn);

        clearBtn = new JButton("清除");
        clearBtn.setFont(font2);
        clearBtn.addActionListener(this);
        panel.add(clearBtn);

        appendCbx = new JCheckBox("附加");
        appendCbx.setFont(font1);
        appendCbx.setSelected(true);
        panel.add(appendCbx);
    }

    private JLabel getLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        if (font != null) {
            label.setFont(font);
        }

        return label;
    }

    private JTextField getTextField(String toolTipText, int columns, Font font) {
        JTextField field = new JTextField();
        field.setFont(font);
        field.setColumns(columns);
        field.setToolTipText(toolTipText);
        field.setHorizontalAlignment(JTextField.CENTER);

        return field;
    }

    // 檢查是否是數值
    private boolean isNumber(String text) {
        try {
            Double.valueOf(text);
            return true;
        } catch (java.lang.NumberFormatException e) {
            return false;
        }
    }

    // 儲存檔案
    private void save(String filename, String text, boolean append) {
        FileWriter fileWriter = null;
        // 新增檔案偵測
        File file = new File(filename);
        boolean exists = file.exists();

        try {
            fileWriter = new FileWriter(filename, append);
            // 不附加狀態寫入Header or 檔案不存在
            if (!append || !exists) {
                fileWriter.write("姓名,國文,英文,數學\n");
            }
            fileWriter.write(text);
            JOptionPane.showMessageDialog(null, "寫入資料成功!");
        } catch (IOException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "寫入資料失敗!");
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void run(boolean visible) {
        setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBtn) {
            String text = outputArea.getText();
            if (text.equals("")) {
                JOptionPane.showMessageDialog(null, "目前資料為空，請先輸入資料!");
                return;
            }

            save(fileName, text, appendCbx.isSelected());

            return;
        }

        if (e.getSource() == clearBtn) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "確定清除所有內容?",
                    "警告", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                outputArea.setText("");
            }
            return;
        }

        if (e.getSource() == addBtn) {
            JTextField[] fields = { nameText, chineseText, englishText, mathText };
            // 檢查輸入是否為空
            for (JTextField field : fields) {
                if (field.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "資料輸入不能為空!");
                    return;
                }
            }

            // 檢查輸入數值是否正確
            for (int i = 1; i < fields.length; i++) {
                if (!isNumber(fields[i].getText())) {
                    JOptionPane.showMessageDialog(null, "分數輸入有錯!");
                    return;
                }
            }

            // 輸出到outputArea
            StringBuilder sb = new StringBuilder();
            for (JTextField field : fields) {
                sb.append(field.getText()).append(",");
            }
            // 移除最後逗點並加上換行符號
            sb.setLength(sb.length() - 1);
            sb.append("\n");
            outputArea.setText(outputArea.getText() + sb.toString());
        }

        return;
    }

}
