package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;


/**
 * The type Client bll.
 */
public class ClientBLL {

	private List<Validator<Client>> validators;
	private ClientDAO clientDAO;

	/**
	 * Thec constructor of the class.
	 */
	public ClientBLL() {
		validators = new ArrayList<>();
		validators.add(new EmailValidator());
		validators.add(new ClientAgeValidator());

		clientDAO = new ClientDAO();
	}

	/**
	 * Find client by id client.
	 *
	 * @param id the id
	 * @return the client
	 */
	public Client findClientById(int id) {
		Client st = clientDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return st;
	}

	/**
	 * Find all clients list.
	 *
	 * @return the list
	 */
	public List<Client> findAllClients(){
		List<Client> c;
		c = clientDAO.findAll();
		if (c.isEmpty()) {
			throw new NoSuchElementException("No client found!");
		}
		return c;
	}

	/**
	 * Insert client.
	 *
	 * @param c the c
	 */
	public void insertClient(Client c){
		clientDAO.insert(c);
	}

	/**
	 * Update client.
	 *
	 * @param c the c
	 */
	public void updateClient(Client c){
		clientDAO.update(c);
	}

	/**
	 * Delete client.
	 *
	 * @param id the id
	 */
	public void deleteClient(int id){
		clientDAO.delete(id);
	}

}
