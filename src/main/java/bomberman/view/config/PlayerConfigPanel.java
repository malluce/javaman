package bomberman.view.config;

import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bomberman.model.arena.ArenaI;
import bomberman.model.player.Player;

/**
 * Used in the view to configure the {@link Player}s.
 * 
 * @author Felix Bachmann
 *
 */
public class PlayerConfigPanel extends JPanel {
	private ArenaConfigPanel arenaConfigPanel;
	private static final String PLAYER_BASE_TEXT = "configuration of player ";
	private static final String LIVES_TEXT = "lives";
	private static final String BOMB_TEXT = "bombs";
	private static final String SPEED_TEXT = "movement speed";

	/*
	 * The ranges of the combo boxes. (e.g. LIVES_RANGE == 3 means the combo box for live selection contains 1,2,3)
	 */
	private static final int LIVES_RANGE = 3;
	private static final int BOMB_RANGE = 3;
	private static final int SPEED_RANGE = 3;
	private static final int DEFAULT_VALUE = 1;

	/*
	 * Used to configure the properties of a player.
	 */
	private JComboBox<Integer> playerChooser;
	private JComboBox<Integer> speedChooser;
	private JComboBox<Integer> lifeChooser;
	private JComboBox<Integer> bombChooser;
	/*
	 * Maps to save the selected values. These are needed because there is only one {@link javax.swing.JComboBox} per
	 * configurable property of a player.
	 */
	private Map<Integer, Integer> speedMap = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> livesMap = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> bombMap = new HashMap<Integer, Integer>();

	/*
	 * keeps track of which player is currently selected. needed to update the maps when the player is changed.
	 */
	private int playerCnt = 1;

	/**
	 * Create a new instance. The arena config panel is needed as reference because the amount of players in the game
	 * (which then can be configured here) is determined by the {@link bomberman.model.arena.ArenaI#getMaxPlayers()}
	 * method.
	 * 
	 * @param arenaConfigPanel
	 *            the arena config panel
	 */
	public PlayerConfigPanel(ArenaConfigPanel arenaConfigPanel) {
		this.arenaConfigPanel = arenaConfigPanel;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		addPlayerConfig();
		initMaps();
	}

	private void initMaps() {
		for (int i = 1; i <= playerChooser.getItemCount(); i++) {
			livesMap.put(i, DEFAULT_VALUE);
			speedMap.put(i, DEFAULT_VALUE);
			bombMap.put(i, DEFAULT_VALUE);
		}
	}

	private void addPlayerConfig() {
		addPlayerSelection();
		addLiveSelection();
		addBombSelection();
		addSpeedSelection();
	}

	private void addPlayerSelection() {
		ArenaI selectedArena = (ArenaI) arenaConfigPanel.getSelectedArena();
		playerChooser = createComboBox(selectedArena.getMaxPlayers());
		playerChooser.addActionListener(e -> {
			updateMapsForPlayer(playerCnt);
			playerCnt = (Integer) playerChooser.getSelectedItem();
			loadFromMaps(playerCnt);
		});

		this.add(createComboBoxPane(PLAYER_BASE_TEXT, playerChooser));
	}

	private void addLiveSelection() {
		lifeChooser = createComboBox(LIVES_RANGE);
		lifeChooser.addActionListener(
				e -> livesMap.put((Integer) playerChooser.getSelectedItem(), (Integer) lifeChooser.getSelectedItem()));
		this.add(createComboBoxPane(LIVES_TEXT, lifeChooser));
	}

	private void addBombSelection() {
		bombChooser = createComboBox(BOMB_RANGE);
		bombChooser.addActionListener(
				e -> bombMap.put((Integer) playerChooser.getSelectedItem(), (Integer) bombChooser.getSelectedItem()));
		this.add(createComboBoxPane(BOMB_TEXT, bombChooser));
	}

	private void addSpeedSelection() {
		speedChooser = createComboBox(SPEED_RANGE);
		speedChooser.addActionListener(
				e -> speedMap.put((Integer) playerChooser.getSelectedItem(), (Integer) speedChooser.getSelectedItem()));
		this.add(createComboBoxPane(SPEED_TEXT, speedChooser));
	}

	private JComboBox<Integer> createComboBox(int range) {
		JComboBox<Integer> box = new JComboBox<Integer>();
		box.setModel(new DefaultComboBoxModel<Integer>());
		for (int i = 1; i <= range; i++) {
			box.addItem(i);
		}
		return box;
	}

	private JPanel createComboBoxPane(String labelText, JComboBox<Integer> box) {
		JPanel comboPane = new JPanel();
		JLabel comboLabel = new JLabel(labelText);
		comboPane.add(comboLabel);
		comboPane.add(box);
		return comboPane;
	}

	private void updateMapsForPlayer(int playerCnt) {
		livesMap.put(playerCnt, (Integer) lifeChooser.getSelectedItem());
		speedMap.put(playerCnt, (Integer) speedChooser.getSelectedItem());
		bombMap.put(playerCnt, (Integer) bombChooser.getSelectedItem());
	}

	/**
	 * Loads the values from the maps into the combo boxes. If there are no values for the player in the map, then the
	 * DEFAULT_VALUE is loaded.
	 * 
	 * @param playerCnt
	 *            the player count to load
	 */
	private void loadFromMaps(int playerCnt) {
		Integer lives = livesMap.get(playerCnt);
		if (lives != null) {
			lifeChooser.setSelectedItem(lives);
		} else {
			livesMap.put(playerCnt, DEFAULT_VALUE);
			lifeChooser.setSelectedItem(DEFAULT_VALUE);
		}

		Integer speed = speedMap.get(playerCnt);
		if (speed != null) {
			speedChooser.setSelectedItem(speed);
		} else {
			speedMap.put(playerCnt, DEFAULT_VALUE);
			speedChooser.setSelectedItem(DEFAULT_VALUE);
		}

		Integer bombs = bombMap.get(playerCnt);
		if (bombs != null) {
			bombChooser.setSelectedItem(bombs);
		} else {
			bombMap.put(playerCnt, DEFAULT_VALUE);
			bombChooser.setSelectedItem(DEFAULT_VALUE);
		}
	}

	/**
	 * Sets the range for the player chooser combo box.
	 * 
	 * @param range
	 *            the new range
	 */
	public void setPlayerRange(int range) {
		int currentRange = playerChooser.getItemCount();

		if (range < currentRange) {
			// items need to be removed
			for (int i = currentRange; i > range; i--) {
				playerChooser.removeItem(i);
			}
		} else if (range > currentRange) {
			// items need to be added and values loaded
			for (int i = currentRange + 1; i <= range; i++) {
				playerChooser.addItem(i);
				loadFromMaps(i);
			}
		}
	}

	/**
	 * Gets the amount of players.
	 * 
	 * @return the player count
	 */
	public int getPlayerCount() {
		return playerChooser.getItemCount();
	}

	/**
	 * Returns the speed for a player. Ids range from 1 to this#getPlayerCount()
	 * 
	 * @param playerId
	 *            the id of the player
	 * @return the speed
	 */
	public int getSpeed(int playerId) {
		return speedMap.get(playerId);
	}

	/**
	 * Returns the lives for a player. Ids range from 1 to this#getPlayerCount()
	 * 
	 * @param playerId
	 *            the id of the player
	 * @return the lives
	 */
	public int getLives(int playerId) {
		return livesMap.get(playerId);
	}

	/**
	 * Returns the bombs for a player. Ids range from 1 to this#getPlayerCount()
	 * 
	 * @param playerId
	 *            the id of the player
	 * @return the bombs
	 */
	public int getBombs(int playerId) {
		return bombMap.get(playerId);
	}

}
