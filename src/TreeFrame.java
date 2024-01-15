import javax.swing.*;

public class TreeFrame extends JFrame {
	public TreeFrame() {
		super("Eric Zhang CS3 Lab 10: Graphic Binary Tree");
		setSize(1900, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(new TreePanel());
		setVisible(true);
	}
}
