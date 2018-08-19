package bomberman;

import java.awt.event.KeyEvent;

import bomberman.controller.Controller;
import bomberman.controller.commands.Command;
import bomberman.controller.exception.NoHandlerForThisCommandException;
import bomberman.controller.handler.HandlerI;
import bomberman.model.Game;
import bomberman.model.config.ControlsConfig;
import bomberman.model.exceptions.IllegalIdRequestException;
import bomberman.model.player.Player;
import utils.Distributer;

/**
 * The main class, contains the game setup and game loop.
 * 
 * @author Felix Bachmann
 *
 */
public final class Main {

	private Main() {

	}

	/**
	 * Contains the main game logic.
	 * 
	 * @param args
	 *            command line arguments
	 * @throws IllegalIdRequestException
	 *             is thrown if more {@link Player}s are supplied to a {@link Game} than indicated by its maxPlayers
	 *             value.
	 * @throws InterruptedException
	 *             is thrown if a thread is interrupted while waiting inside game loop
	 * @throws NoHandlerForThisCommandException
	 *             is thrown if the {@link Controller} is called to handle a {@link Command} but there is no
	 *             {@link HandlerI} for this command.
	 */
	public static void main(String[] args)
			throws IllegalIdRequestException, InterruptedException, NoHandlerForThisCommandException {
		/*
		 * supply sprite names to the sprite distributer
		 */
		Distributer<String> spriteNameDistr = new Distributer<String>();
		spriteNameDistr.add("player_one.png");
		spriteNameDistr.add("player_two.png");

		/*
		 * supply instances of ControlsConfig to the controlDistributer
		 */
		Distributer<ControlsConfig> controlDistributer = new Distributer<ControlsConfig>();
		controlDistributer.add(new ControlsConfig(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
				KeyEvent.VK_NUMPAD0, KeyEvent.VK_ENTER));
		controlDistributer.add(new ControlsConfig(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D,
				KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE));

		/**
		 * Create a controller.
		 */
		Controller ctrlr = new Controller(spriteNameDistr, controlDistributer);

		/**
		 * Let the magic happen, start the game configuration and let the user use the view from now on.
		 */
		ctrlr.handle(Command.START_CONFIG);
	}

}
