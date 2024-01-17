import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TreePanel extends JPanel implements MouseListener {
	
	private JTextField box;
	private BinarySearchTree tree;
	
	private boolean showError;
	
	public TreePanel() {
		setLayout(null);
		setBackground(new Color(195, 195, 254));
		JLabel title = new JLabel("Graphic Binary Tree");
		title.setFont(new Font("Cambria", Font.BOLD, 50));
		title.setBounds(707, 10, 600, 90);
		add(title);
		
		box = new JTextField(8);
		box.setBounds(1060, 900, 120, 30);
		box.setFont(new Font("Garamond", Font.BOLD, 25));
		add(box);
		
		tree = new BinarySearchTree();
		showError = false;
		
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		setFont(new Font("Cambria", Font.PLAIN, 25));
		g.drawString("Please enter a number to add to the tree: ", 612, 922);
		
		g.setColor(new Color(73, 0, 0));
		g.fillOval(1190, 885, 100, 60);
		g.setColor(Color.white);
		g.setFont(new Font("Calibri", Font.BOLD, 25));
		g.drawString("ADD", 1215, 922);
		
		
		// draw edges
		
		// draw nodes
		g.fillOval(915, 100, 50, 50); // LEVEL 0 nodes
		
		g.fillOval(440, 240, 50, 50); // LEVEL 1 nodes
		g.fillOval(1390, 240, 50, 50);
		
		for (int i = 0; i < 4; i++) { // LEVEL 2 nodes
			g.fillOval(238 + i * 460, 380, 50, 50);
		}
		
		for (int i = 0; i < 8; i++) { // LEVEL 3 nodes
			g.fillOval(119 + i * 230, 520, 50, 50);
		}
		
		for (int i = 0; i < 16; i++) { // LEVEL 4 nodes
			g.fillOval(60 + i * 115, 660, 50, 50);
		}
		
		for (int i = 0; i < 32; i++) { // LEVEL 5 nodes
			g.fillOval(30 + i * 58, 800, 50, 50);
		}
		
		// fill in the nodes
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		if (x >= 1190 && x <= 1290 && y >= 885 && y <= 945) {
			String s = box.getText();
			if (isInteger(s)) {
				BinaryNode bn = new BinaryNode(Integer.parseInt(s));
				box.setText("");
				tree.add(bn);
				tree.fillLevels();
				
				System.out.println(tree.levelOrder()); // just for testing, can remove this line later
				System.out.println(Arrays.toString(tree.level0()));
				System.out.println(Arrays.toString(tree.level1()));
				System.out.println(Arrays.toString(tree.level2()));
				System.out.println(Arrays.toString(tree.level3()));
				System.out.println(Arrays.toString(tree.level4()));
				System.out.println(Arrays.toString(tree.level5()));
			}
			else {
				showError = true;
			}
		}
	}
	
	

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static boolean isInteger(String s) {
		for (char c : s.toCharArray()) {
			if (c < 48 || c > 57) {
				return false;
			}
		}
		return true;
	}

}
