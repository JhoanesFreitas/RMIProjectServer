package br.com.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote{
	
	public boolean conectar(ChatClient client, String nickName) throws RemoteException;
	public void desconectar(ChatClient client, String nickName) throws RemoteException;
	public void falar(String nickName, String msg) throws RemoteException;
	public String[] naSessao() throws RemoteException;
}
