package br.com.server;

import java.awt.Component;
import java.awt.List;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Hashtable<String, ChatClient> usuariosRegistrados = new Hashtable<String, ChatClient>();

	protected ChatServerImpl() throws RemoteException {
		super();
	}

	public boolean conectar(ChatClient client, String nickName) throws RemoteException {
		if (nickName.equals(nickName)) {
			usuariosRegistrados.put(nickName, client);
			client.evento("Conected!" + "\n\n" + nickName + ": ");
			onConnection(nickName, true);
			return true;
		}
		client.evento("Indisponíve!");
		return false;
	}

	public void desconectar(ChatClient client, String nickName) throws RemoteException {
		client.evento("Você saiu da conversa!");
		onConnection(nickName, false);
		usuariosRegistrados.remove(nickName);
	}

	public void falar(String nickName, String msg) throws RemoteException {
		Enumeration<ChatClient> users = usuariosRegistrados.elements();
		ChatClient clientOwner = usuariosRegistrados.get(nickName);

		while (users.hasMoreElements()) {
			ChatClient client = users.nextElement();
			
			if(!clientOwner.equals(client))
				client.disse(nickName, msg);
			else
				client.evento(nickName + ": ");
		}
	}
	
	private void onConnection(String nickName, boolean owner) throws RemoteException{
		Enumeration<ChatClient> users = usuariosRegistrados.elements();
		ChatClient clientOwner = usuariosRegistrados.get(nickName);

		while (users.hasMoreElements()) {
			ChatClient client = users.nextElement();
			
			if(!clientOwner.equals(client) && owner)
				client.evento("\n----------------------------\n" + nickName + " entrou na conversa!" + "\n----------------------------\n");
			else if(!owner)
				client.evento("\n----------------------------\n" + nickName + " saiu da conversa!" + "\n----------------------------\n");
			
		}
	}

	public String[] naSessao() throws RemoteException {

		return usuariosRegistrados.keySet().toArray(new String[0]);
	}

}
