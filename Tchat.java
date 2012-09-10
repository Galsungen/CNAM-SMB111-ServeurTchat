
/*
 * @author Galsungen - http://blog.galsungen.net
 * SMB111 - Systèmes et applications répartis - 2011-12 - Exercice 3 - Tchat RMI
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Tchat extends UnicastRemoteObject implements TchatInterface {

    //variables globales
    private HashMap<String, ArrayList<Message>> tableau = new HashMap<String, ArrayList<Message>>();

    public Tchat() throws RemoteException {
            super();
    }

    @Override
    public synchronized void envoyerMessage (Message message) throws RemoteException {
        //test si pseudo absent hashmap, on ajoute
        String pseudo = message.getPseudo();
        if(!tableau.containsKey(pseudo)) {
            tableau.put(pseudo, new ArrayList<Message>());
        }

        //on parcours la HashMap et si pseudo (clef) différent de pseudo on insère le message
        for(Entry<String, ArrayList<Message>> entry : tableau.entrySet()) {
                if(!entry.getKey().equals(pseudo)){
                entry.getValue().add(message);
            }
        }
    }

    @Override
    public synchronized ArrayList<Message> lireMessage(String pseudo) throws RemoteException {

        ArrayList<Message> reponse = new ArrayList<Message>();

        if(tableau.containsKey(pseudo)) {
            ArrayList<Message> tmp = tableau.get(pseudo);
            reponse = tableau.get(pseudo);
            //tmp.clear();
        }

        return reponse;
    }

    @Override
    public void nettoyerMessage(String pseudo) throws RemoteException {

        if(tableau.containsKey(pseudo)) {
            ArrayList<Message> tmp = tableau.get(pseudo);
            tmp.clear();
        }

    }



}