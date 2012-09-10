#!/bin/sh

NOM_IP = $1

rmic -v1.2 Tchat
rmiregistry -J-Djava.security.policy=client.policy &
java TchatServer $1 &