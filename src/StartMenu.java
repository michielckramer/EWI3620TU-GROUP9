import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.j2d.TextRenderer;

public class StartMenu implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	private JButton startButton = new JButton("Start Game");
	private JButton exitButton = new JButton("Exit Game");
	private JTextArea text = new JTextArea("Escape the prison! ");

	public StartMenu() {
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		startButton.addActionListener(this);
		exitButton.addActionListener(this);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double w = screenSize.getWidth();
		int width = (int) w;
		double h = screenSize.getHeight();
		int height = (int) h;

		startButton.setBounds(width / 10, height / 10, width / 5, height / 10);
		exitButton.setBounds(7 * width / 10, height / 10, width / 5,
				height / 10);

		Font font = new Font("Times", Font.BOLD, 32);
		text.setBounds(4 * width / 10, height / 10, width / 5, height / 10);
		text.setBackground(Color.lightGray);
		text.setSize(300, 50);
		text.setFont(font);

		JPanel content = new JPanel();
		content.setLayout(null);
		content.add(startButton);
		content.add(exitButton);
		content.add(text);
		frame.add(content);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();
		if (src == startButton) {
			new MazeRunner(1);
			frame.dispose();
		}
		if (src == exitButton) {
			frame.dispose();
		}
	}
}