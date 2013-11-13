import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartMenu implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	private JButton startButton = new JButton("Start Game");
	private JButton exitButton = new JButton("Exit Game");

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

		JPanel content = new JPanel();
		content.setLayout(null);
		content.add(startButton);
		content.add(exitButton);
		frame.add(content);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();
		if (src == startButton) {
			new MazeRunner();
		}
		if (src == exitButton) {
			frame.dispose();
		}

	}
}
