package pl.kotus.turingmachine.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;
import pl.kotus.turingmachine.utils.Machine;

/**
 *
 * @author Adam
 */
public class MainWindow extends JFrame implements ActionListener {

    Machine m = new Machine();
    ProgramWindow pw = new ProgramWindow();
    private final int sizeOfChar = 40;
    char[] c = new char[sizeOfChar];
    int position = sizeOfChar / 2;
    private final Integer height = 200;
    private final Integer width = 1000;

    JButton start;
    JTextArea textArea;

    Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize(); // wielkość ekranu do ustawienia środka

    public MainWindow() {
        super("Turing Machine");
        setSize(width, height);
        setVisible(true);
        pw.setVisible(true);
        pw.textArea.setFont(new Font("Font", Font.PLAIN, 20));
        this.setLocation(monitorSize.width / 2 - this.getWidth() / 2, monitorSize.height / 2 - this.getHeight() / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        start = new JButton("Krok");
        this.add(start);
        start.setActionCommand("krok");
        start.addActionListener(this);
        textArea = new JTextArea();
        this.add(textArea);
        textArea.setFont(new Font("Times", Font.PLAIN, 30));
        textArea.setText(getStr1(chars(c)));
    }

    private String getStr1(char[] c) {
        String text = " ";
        for (int i = 0; i < c.length; i++) {
            text = text + c[i] + " ";
        }
        return text;
    }
    

    private char[] chars(char[] c) {
        if (c[0] == '\u0000') {
            for (int i = 0; i < sizeOfChar; i++) {
                c[i] = '#';
            }
        } else {
            this.c = c;
        }
        return c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("krok")) {
            m.reader(pw.textArea.getText());
            if(m.compilation().length() > 2) {
                JOptionPane.showMessageDialog(this, m.compilation());
            } else if (m.setOperation(m.getState(), c[position])) {
                c[position] = m.getnSaint();
                this.textArea.setText(getStr1(c));
                m.setState(m.getNstate());
                if (m.getDirection() == 'P' || m.getDirection() == 'p') {
                    position++;
                } else if (m.getDirection() == 'L' || m.getDirection() == 'l') {
                    position--;
                }
            }
        }
    }

    public void windowClosing(WindowEvent e) {
        dispose();
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
