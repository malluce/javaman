package bomberman.controller.handler;

import bomberman.controller.Controller;
import bomberman.controller.commands.Command;
import bomberman.view.config.GameConfigFrame;

/**
 * Handles the {@link bomberman.controller.commands.Command#START_CONFIG} command.
 * 
 * @author Felix Bachmann
 *
 */
public class ConfigStartHandler implements HandlerI {
	private Controller ctrlr;
	private GameConfigFrame gcf;

	/**
	 * Creates a new ConfigStartHandler with a controller.
	 * 
	 * @param ctrlr
	 *            the controller
	 */
	public ConfigStartHandler(Controller ctrlr) {
		this.ctrlr = ctrlr;
	}

	@Override
	public void handle(Command comm) {
		if (comm != Command.START_CONFIG) {
			throw new UnsupportedOperationException("Cannot handle this command.");
		}

		gcf = new GameConfigFrame(ctrlr);
		ctrlr.setGameConfigFrame(gcf);
	}

}
