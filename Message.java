
/*
 * @author Galsungen - http://blog.galsungen.net
 * SMB111 - Systèmes et applications répartis - 2011-12 - Exercice 3 - Tchat RMI
 */


import java.io.Serializable;

public class Message implements Serializable {

    //variables de classe
    private String pseudo, phrase;

    public Message() {
        super();
    }

    public Message(String pseudo, String phrase){
        this.pseudo = pseudo;
        this.phrase = phrase;
    }

    @Override
    public String toString(){
        return "<"+pseudo+"> "+phrase+"\n";
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

}
