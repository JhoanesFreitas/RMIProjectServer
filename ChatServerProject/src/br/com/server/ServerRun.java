package br.com.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class ServerRun {

	public static void main(String[] args) {
		try{
			LocateRegistry.createRegistry(1099);
			ChatServer conectar = new ChatServerImpl();
			Naming.rebind("//localhost/conectar", conectar);
			System.out.println("Servidor pronto para autenticar");
		}catch(RemoteException e){
			e.printStackTrace();
		}catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
