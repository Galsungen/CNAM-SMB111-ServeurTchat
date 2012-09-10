
/*
 * @author Galsungen - http://blog.galsungen.net
 * SMB111 - Systèmes et applications répartis - 2011-12 - Exercice 3 - Tchat RMI
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TchatInterface extends Remote {
	void envoyerMessage(Message message) throws RemoteException;
	ArrayList<Message> lireMessage(String pseudo) throws RemoteException;
    void nettoyerMessage(String pseudo) throws RemoteException;
}