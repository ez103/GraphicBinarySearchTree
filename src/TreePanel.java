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
		JLabel title = new JLabel("Graphic Binary Search Tree");
		title.setFont(new Font("Cambria", Font.BOLD, 50));
		title.setBounds(640, 10, 649, 90);
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
		g.drawString("Please enter a number to add to or remove from the tree: ", 450, 922);
		
		g.setColor(new Color(73, 0, 0));
		g.fillOval(1190, 885, 100, 60); // add button
		g.fillOval(1300, 885, 100, 60); // remove button
		g.setColor(Color.white);
		g.setFont(new Font("Calibri", Font.BOLD, 25));
		g.drawString("ADD", 1215, 922);
		g.drawString("REMOVE", 1303, 922);
		
		
		// draw edges
		g.setColor(Color.black);
		g.drawLine(940, 125, 465, 265); // LEVEL 0 to 1 edge
		g.drawLine(940, 125, 1415, 265);
		
		g.drawLine(465, 265, 263, 405); // LEVEL 1 to 2 edge
		g.drawLine(465, 265, 732, 405);
		g.drawLine(1413, 265, 1200, 405); 
		g.drawLine(1413, 265, 1668, 405);
		
		for (int i = 0; i < 4; i++) { // LEVEL 2 to 3 edge
			g.drawLine(263 + 460 * i, 405, 158 + 460 * i, 525); 
			g.drawLine(263 + 460 * i, 405, 370 + 460 * i, 525); 
		}
		
		for (int i = 0; i < 8; i++) { // LEVEL 3 to 4 edge
			g.drawLine(148 + 230 * i, 525, 77 + 230 * i, 685);
			g.drawLine(146 + 230 * i, 525, 198 + 230 * i, 685);
		}
		
		for (int i = 0; i < 16; i++) { // LEVEL 4 to 5 edge
			g.drawLine(86 + 115 * i, 685, 56 + 116 * i, 825);
			g.drawLine(86 + 115 * i, 685, 114 + 116 * i, 825);
		}
		
		// draw nodes
		g.setColor(Color.white);
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
		g.setFont(new Font("Garamond", Font.BOLD, 19));
		g.setColor(Color.black);
		if (tree.level0().length != 0 && tree.level0()[0] != null && (int)tree.level0()[0] != -6969911) { // root node
			g.drawString("" + tree.level0()[0], 928, 132);
		}
		
		if (tree.level1().length != 0 && tree.level1()[0] != null  && (int)tree.level1()[0] != -6969911) { // LEVEL 1 nodes
			g.drawString("" + tree.level1()[0], 452, 272);
		}
		if (tree.level1().length != 0 && tree.level1()[1] != null && (int)tree.level1()[1] != -6969911) { 
			g.drawString("" + tree.level1()[1], 1402, 272);
		}
		
		for (int i = 0; i < 4; i++) {
			if (tree.level2().length != 0 && tree.level2()[i] != null && (int)tree.level2()[i] != -6969911) { // LEVEL 2 nodes
				g.drawString("" + tree.level2()[i], 251 + i * 460, 412);
			}
		}
		
		for (int i = 0; i < 8; i++) {
			if (tree.level3().length != 0 && tree.level3()[i] != null && (int)tree.level3()[i] != -6969911) { // LEVEL 3 nodes
				g.drawString("" + tree.level3()[i], 131 + i * 230, 552);
			}
		}
		
		for (int i = 0; i < 16; i++) {
			if (tree.level4().length != 0 && tree.level4()[i] != null && (int)tree.level4()[i] != -6969911) { // LEVEL 4 nodes
				g.drawString("" + tree.level4()[i], 71 + i * 115, 692);
			}
		}
		
		for (int i = 0; i < 32; i++) {
			if (tree.level5().length != 0 && tree.level5()[i] != null && (int)tree.level5()[i] != -6969911) { // LEVEL 5 nodes
				g.drawString("" + tree.level5()[i], 40 + i * 58, 832);
			}
		}
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
				BinaryNode bn;
				if (s.charAt(0) == '-') {
					bn = new BinaryNode((-1) *Integer.parseInt(s.substring(1)));
				}
				else {
					bn = new BinaryNode(Integer.parseInt(s));
				}
				
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
		
		// g.fillOval(1300, 885, 100, 60); // remove button
		else if (x >= 1300 && x <= 1400 && y >= 885 && y <= 945) {
			String s = box.getText();
			if (isInteger(s)) {
				int boo;
				if (s.charAt(0) == '-') {
					boo = (-1) * Integer.parseInt(s.substring(1));
				}
				else {
					boo = Integer.parseInt(s);
				}
				
				box.setText("");
				tree.remove(boo);
				tree.fillLevels();
				
				System.out.println(tree.levelOrder()); // just for testing, can remove this line later
				System.out.println(Arrays.toString(tree.level0()));
				System.out.println(Arrays.toString(tree.level1()));
				System.out.println(Arrays.toString(tree.level2()));
				System.out.println(Arrays.toString(tree.level3()));
				System.out.println(Arrays.toString(tree.level4()));
				System.out.println(Arrays.toString(tree.level5()));
			}
		}
		
		repaint();
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
		if (s.charAt(0) == '-' && s.length() > 1) { // could be a negative integer
			return isInteger(s.substring(1));
		}
		for (char c : s.toCharArray()) {
			if (c < 48 || c > 57) {
				return false;
			}
		}
		return true;
	}

}
