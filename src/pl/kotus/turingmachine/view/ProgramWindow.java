package pl.kotus.turingmachine.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;

/**
 *
 * @author Adam
 */
public class ProgramWindow extends JFrame {

    private final Integer height = 800;
    private final Integer width = 300;

    JTextArea textArea;

    public ProgramWindow() {
        super("Program");
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(1200, 100);
        textArea = new JTextArea("Start:#/0:P:Stan1\nStan1:#/1:P:Stan2");
        this.add(textArea);
        
        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
        jScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jScrollPane);
    }
}
