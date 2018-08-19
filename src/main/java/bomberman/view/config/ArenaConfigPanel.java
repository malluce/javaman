package bomberman.view.config;

import java.util.Iterator;
import java.util.ServiceLoader;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bomberman.model.arena.ArenaI;

/**
 * Used in the view to configure the arena of the game.
 * 
 * @author Felix Bachmann
 *
 */
public class ArenaConfigPanel extends JPanel {
	private static final String ARENA_TEXT = "Choose your arena:";
	private JComboBox<ArenaI> arenaChooser;
	private PlayerConfigPanel pcp;

	/**
	 * Creates a new instance without a {@link PlayerConfigPanel}. This must be added later by calling
	 * {@link #setPlayerConfigPanel(PlayerConfigPanel)}
	 */
	public ArenaConfigPanel() {
		init();
	}

	/**
	 * Creates a new instance. The supplied {@link PlayerConfigPanel} is influenced by arena selection because the arena
	 * determines how many players there are.
	 * 
	 * @param pcp
	 *            the PlayerConfigPanel
	 */
	public ArenaConfigPanel(PlayerConfigPanel pcp) {
		this.pcp = pcp;
		init();
	}

	private void init() {
		this.add(new JLabel(ARENA_TEXT));
		addArenaChooser();
	}

	private void addArenaChooser() {
		arenaChooser = new JComboBox<ArenaI>();
		arenaChooser.addItemListener(e -> {
			// pcp may be null when pcp not set yet, thus the null check
			if (pcp != null) {
				pcp.setPlayerRange(((ArenaI) arenaChooser.getSelectedItem()).getMaxPlayers());
			}
		});

		// load available subclasses of ArenaI and add them to the combo box
		ServiceLoader<ArenaI> arenaLoader = ServiceLoader.load(ArenaI.class);
		Iterator<ArenaI> arenaIter = arenaLoader.iterator();
		assert (arenaIter.hasNext());
		while (arenaIter.hasNext()) {
			arenaChooser.addItem(arenaIter.next());
		}

		this.add(arenaChooser);
	}

	/**
	 * @return the currently selected {@link bomberman.model.arena.ArenaI}
	 */
	public ArenaI getSelectedArena() {
		return (ArenaI) arenaChooser.getSelectedItem();
	}

	/**
	 * Sets the {@link PlayerConfigPanel}.
	 * 
	 * @param pcp
	 *            the new {@linkplain PlayerConfigPanel}
	 */
	public void setPlayerConfigPanel(PlayerConfigPanel pcp) {
		this.pcp = pcp;
	}

}
