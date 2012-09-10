
/*
 * @author Galsungen - http://blog.galsungen.net
 * SMB111 - Systèmes et applications répartis - 2011-12 - Exercice 3 - Tchat RMI
 */

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsulterServeur extends Thread {

    private TchatRMI fenetre;

    public ConsulterServeur (TchatRMI fenetre) {
        super();
        this.fenetre = fenetre;
    }

    @Override
    public void run () {

    }

}
