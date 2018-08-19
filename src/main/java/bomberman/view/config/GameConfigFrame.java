package bomberman.view.config;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bomberman.controller.Controller;
import bomberman.controller.commands.Command;
import bomberman.controller.exception.NoHandlerForThisCommandException;

/**
 * The main frame for the player and game configuration.
 * 
 * @author Felix Bachmann
 *
 */
public class GameConfigFrame extends JFrame {
	private JPanel mainPane;
	private Controller ctrlr;
	private ArenaConfigPanel acp;
	private PlayerConfigPanel pcp;

	/**
	 * Creates a new instance.
	 * 
	 * @param ctrlr
	 *            the controller. {@code ctrlr.setGameConfigFrame(this)} will be called
	 */
	public GameConfigFrame(Controller ctrlr) {
		this.ctrlr = ctrlr;
		ctrlr.setGameConfigFrame(this);

		mainPane = new JPanel();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
		acp = new ArenaConfigPanel();
		mainPane.add(acp);
		pcp = new PlayerConfigPanel(acp);
		acp.setPlayerConfigPanel(pcp);
		mainPane.add(pcp);
		addStartButton();

		SwingUtilities.invokeLater(() -> {
			this.setAlwaysOnTop(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("javaman configuration");
			this.setPreferredSize(new Dimension(300, 300));
			this.add(mainPane);
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		});

	}

	private void addStartButton() {
		JPanel startPane = new JPanel();
		JButton button = new JButton("Start the game!");
		button.addActionListener(e -> {
			// start a new thread because..
			Thread gameThread = new Thread(() -> {
				try {
					// ..the game loop (infinite loop!) will be entered after the following call
					ctrlr.handle(Command.START_GAME);
				} catch (NoHandlerForThisCommandException e1) {
					e1.printStackTrace();
				}
			});
			gameThread.start();

		});
		startPane.setLayout(new BoxLayout(startPane, BoxLayout.LINE_AXIS));
		startPane.add(button);
		mainPane.add(startPane);
	}

	/**
	 * Returns the {@link ArenaConfigPanel} which is a panel in this {@link GameConfigFrame}.
	 * 
	 * @return the arena config panel
	 */
	public ArenaConfigPanel getArenaConfigPanel() {
		return acp;
	}

	/**
	 * Returns the {@link PlayerConfigPanel} which is a panel in this {@link GameConfigFrame}.
	 * 
	 * @return the player config panel
	 */
	public PlayerConfigPanel getPcpPanel() {
		return pcp;
	}

}
