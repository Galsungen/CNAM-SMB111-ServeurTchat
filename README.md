# Serveur de Tchat (Java RMI) #

## Matière / UE ##

 * SMB111 : Systèmes et applictaions répartis
 * Centre FOD Interrégional de la Réunion
 * Année de réalisation : 2012

## Avertissement ##

Je met à disposition ces sources et ce sujet pour accompagner de nouveaux auditeurs dans les matières concernées. En aucun cas il ne s'agit de "la" solution au sujet, mais simplement de ma solution. Ne recopiez jamais le code d'un autre sans le comprendre, et surtout pas pour le rendre au formateur. Et même en le comprenant d'ailleurs dans le cadre du CNAM ... Ecrivez votre propre code, votre propre solution, vous verrez que vous en apprendrez bien plus. Ma démarche est juste de vous aider en vous donnant une réprésentation possible d'une solution. Je sais que moi cela m'aide comme démarche, et que j'écris ensuite toujours ma solution, avec ma vision.

Bon courage à tous si vous êtes dans le cursus du CNAM (Conservatoire National des Arts et Métiers).

## Sujet ##

Il s'agit d'écrire un serveur qui permet de faire communiquer différentes personnes.
* Un client aura la possibilité de se connecter au serveur de Chat puis de choisir un pseudo pour ensuite pouvoir envoyer des messages au serveur.
* Le serveur renverra à tous les clients connectés les messages reçus.

## Technologies à utiliser ##

Utiliser au choix .NET, JSP, Servlet ou Java RMI. La technologie devant changer à chaque exercice, j'ai choisi d'utiliser ici Java RMI.

## Description des fichiers ##

* TchatInterface.java : Il s’agit de l’interface qui décrit l’objet distribué qui va être utilisé. On y trouve la description des méthodes qui seront implémentées dans l’objet.
* Tchat.java : C’est l’implémentation de l’objet distribué. C’est lui qui va être utilisé par les clients. Il contient les méthodes qui ici vont permettre au serveur de fonctionner et donc aux clients de communiquer avec le serveur et à travers lui entre eux. Nous y trouverons donc les méthodes « envoyerMessage », « lireMessage » et « nettoyerMessage ».
* TchatServer.java : C’est le serveur en lui-même qui met à disposition l’objet implémenté. Il tourne ici par défaut sur le port 1099 et attend les appels des clients.
* client.policy : C’est le fichier de sécurité pour le serveur RMI. Bien que très « laxiste » dans sa version présente, il peut permettre de gérer la sécurité d’accès au serveur.
* Message.java : Il s’agit d’un objet secondaire. En effet le client va échanger avec le serveur RMI, à travers l’objet implémenté, des objets « Message » qui contiennent son pseudonyme, son message proprement dit.
* ConsulterServeur.java : Il s’agit d’un objet threadé qui va nous permettre de consulter régulièrement le serveur de Tchat depuis le client.
* TchatRMI.java & TchatRMI.form : Le client de Tchat proprement dit avec son formulaire pour l’interface graphique. C’est lui qui va utiliser l’objet distribué et qui va interroger le serveur. Il sera exécuté par chacune des personnes souhaitant se connecter au serveur de Tchat.
* launch.bat / launch.sh : Petits scripts permettant de lancer la partie serveur et d’enregistrer dans le rmiregistry l’objet distribué. Leur utilisation est abordée par la suite, après la description de la méthode manuelle.

## Comment exécuter le code ##

Il vous faudra d'abord générer les fichiers .class. Pour cela placez-vous dans le dossier où vous avez les fichiers .java et exécutez la commande "javac *.java" dedans. Il faudra bien spur avoir un JDK d'installé et de fonctionnel sur la machine. Le code est en Java 1.6.

Il est possible d’exécuter l’application en trois étapes, manuellement, en ligne de commandes. Se placer dans le dossier contenant les fichiers « .class » et exécuter les commandes suivantes sur le serveur :
Lancer rmic sur la classe d’implémentation :
<pre>
  rmic –v1.2 Tchat
</pre>
Nous aurons alors plusieurs fichiers dont un ou plusieurs fichiers « .stub » (ici : « Tchat_Stub.class »). Pour pouvoir exécuter notre client sur une autre machine il nous faudra copier *Stub.class, TchatInterface.class et les objets propres au client sur l’autre machine. Tous les fichiers sauf celui du serveur et de l’objet distribué donc. Vous pouvez simplifier en copiant tout le dossier, cela fonctionnera aussi. Nous ne démarrerons simplement que le client sur la deuxième machine.
Nous démarrons ensuite le RMI registry :
<pre>
	rmiregistry -J-Djava.security.policy=client.policy&
</pre>
Et enfin nous lançons le serveur :
<pre>
	java TchatServer IP_de_notre_Serveur &
</pre>

Attention ici il faut remplacer « IP_de_notre_serveur » par l’ip extérieure de notre machine. En effet si nous la lançons sur localhost ou 127.0.0.1, elle ne sera accessible qu’en local, ce qui limite pour un Tchat.
A ce moment là notre serveur tourne et est en attente des appels des clients. Il est aussi possible de gagner du temps. En effet se trouve dans le dossier avec les fichiers, deux autres fichiers qui peuvent servir de lanceurs :
* Windows : launch.bat
* Linux : launch.sh

Ils permettent suivant votre système d’exploitation, d’exécuter les trois commandes précédentes en une seule. Ils prennent tous les deux en paramètre l’IP sur laquelle on veut lancer notre serveur. Par exemple sur une machine sous Windows dont l’IP sur le réseau est 192.168.0.31, la commande sera la suivante :
<pre>
	C:\>launch.bat 192.168.0.31
</pre>
Pour exécuter un client, il nous faut lancer dans une nouvelle invite de commande la ligne suivante :
<pre>
	java -Djava.security.policy=client.policy TchatRMI
</pre>
Cela doit ouvrir l’interface graphique d’un client. Comme nous pouvons le voir sur l’image précédente, par défaut au lancement, le bouton pour envoyer les messages est grisé. En effet il faut être connecté pour qu’il s’active. La connexion désactivera la modification des champs « Pseudo », « IP Serveur » et le bouton « Connecter ».
Afin de tester il suffit de lancer un deuxième client, soit sur la machine elle-même, soit sur une autre machine qui puisse communiquer avec celle hébergeant le serveur sur le réseau. Il faut bien sur copier les fichiers nécessaires à l’exécution de la partie cliente, mais aussi le fichier d’Interface et le fichier Stub avant de l’exécuter. Sur cette machine nous n’avons utilisé que la dernière commande donnée plus haut, permettant d’exécuter le client.

## Limitations et évolutions ##

Actuellement l’application présente plusieurs limitations. Certaines plus simples que d’autres à corriger ou à faire évoluer.

* Il manque un bouton « déconnecter », ou bien la transformation du bouton de connexion pour permettre la déconnexion du serveur sans avoir à fermer et relancer le client.
* Nous n’empêchons pas l’utilisation de deux pseudonymes différents sur deux clients distincts. Il est donc facile d’usurper l’identité de quelqu’un.
* Nous ne réalisons aucune identification des clients se connectant. N’importe qui disposant du client peut se connecter au serveur. Nous pourrions le protéger par un système de login / mot de passe, de clefs, ou une liste de clients autorisés sur leurs IP ou adresses MAC.
* Les flux ne sont pas cryptés entre les clients et les serveurs. Ils peuvent donc être interceptés et analysés.
* Quand on écrit un message et qu’on l’envoi, la fenêtre de texte reste remplie. Un vidage automatique après envoi serait un plus.
* Dans la fenêtre affichant le texte reçu par le serveur, nous n’avons pas imposé de limite. Nous pourrions convenir de le vider toutes les 100 lignes par exemple. Ou nous pourrions le vider toutes les 100 lignes, mais réaliser un stockage complet soit dans un fichier de log local, ou une petite base de données intégrée. De même le serveur pourrait conserver un historique complet des connexions, identifications et discussions en base de données ou dans un fichier local.
* Nous ne gérons pas la déconnexion des clients, qu’elle soit manuelle via une demande, ou car le client est fermé ou ne répond plus. En plus d’un bouton de déconnexion, il pourrait être convenu qu’au bout de 100 ou 1000 lignes sans demande de lecture de la part d’un client, on conclu que ce dernier n’est plus connecté, et le serveur couperait alors automatiquement sa connexion, en vidant les espaces mémoire concernés.
* Nous pourrions afficher aussi dans un panneau latéral, les pseudonymes des contacts connectés, et les en retirer à la déconnexion, en plus d’un message dans la fenêtre de discussion.
* Nous gérons très mal les levées d’Exceptions. En effet nous pourrions affiner leurs levées et les retours qu’elles font de façon à expliquer clairement et en langage courant les problèmes rencontrés. Ce sera bien plus facile à comprendre pour des néophytes.
* Pour le lancement du serveur nous pourrions faire une détection des adresses IP et donner le choix de l’IP, ou des IP, sur laquelle lancer le serveur. Cela pourrait se faire en ligne de commandes, ou bien par une petite interface graphique. Nous pourrions coupler cet aspect avec un système de logs en temps réel pour le serveur.

De nombreuses autres améliorations pourraient voir le jour par la suite, comme la mise en place de discussions privées, de salons distincts, …

