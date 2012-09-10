
/*
 * @author Galsungen - http://blog.galsungen.net
 * SMB111 - Systèmes et applications répartis - 2011-12 - Exercice 3 - Tchat RMI
 */

import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class TchatServer {
	public static void main(String[] args) {

		try {
            String adresse = args[0];
			System.out.println( "Serveur : Construction de l'implementation ");
			Tchat chat= new Tchat();
			System.out.println("Objet Tchat lie dans le RMIregistry");
			Naming.rebind("rmi://"+adresse+":1099/MyTchat", chat);
			System.out.println("Attente des invocations des clients ...");
		}
		catch (Exception e) {
			System.out.println("Erreur de liaison de l'objet Tchat");
			System.out.println(e.toString());
		}
	} //fin du main
} //fin de la classe