rmic -v1.2 Tchat
start rmiregistry -J-Djava.security.policy=client.policy &
java TchatServer %1 &