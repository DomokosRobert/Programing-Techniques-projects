package start;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;
import bll.OrderrBLL;
import bll.ProductBLL;
import model.Client;
import model.Orderr;
import model.Product;
import presentation.GUI;

import javax.swing.*;


/**
 * The type Start.
 */
public class Start {
	/**
	 * The constant LOGGER.
	 */
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 * @throws SQLException the sql exception
	 */
	public static void main(String[] args) throws SQLException {

		JFrame frame = new JFrame("Order manager");
		GUI fr = new GUI();
		frame.setContentPane(fr.getFirstPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);


	}

}
