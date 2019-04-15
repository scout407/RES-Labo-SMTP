# RES-Labo-SMTP

- Auteur : Bouyiatiotis Stéphane, Flückiger Nathan
- Aide   : Olivier Liechti

# Description
Dans ce dépos se trouve le laboratoire SMTP pour le cours de RES. 
Le but de ce laboratoire est de pouvoir envoyé des faux mails à des personnes en se faisant passé pour une autre personne (mail forgé)

Les classe pemettant de gérer la création du mail sont :
  - Victim, EMail et group permettant de réalisé les groupes de personnes qui recevrons le mail
  - Prank permet de générer le spam email
  - PrankGenerator permet de créer les groupes (avec celui qui envoie et ceux qui reçoivent) 
  - ConfigManager lui permet de géréer les listes des personnes et des messages
  - SmtpClient lui est utilisé pour se connecter aux serveur et envoyé les messages



# Fonctionnement
Pour configManager il faut messages.utf8 pour les messages et victims.utf8 pour la liste des victimes

Pour message.utf8
  - Pour mettre un sujet il faut placer Subject: <le sujet> 
  - Ensuite écrire le body directement après et laissé une ligne vide pour la fin  
  - Ne lit pas les ligne vide
  
Pour victim.utf8
  - Juste une adresse email par ligne
  
# Docker et MockMock

Comme présenté il faut utilisé docker pour le labo

S'assuré d'être dans la racine du projet où se trouve le Dockerfile

Build le docker avec la commande : ```docker build -t mock-smtp-server . ```

Ensuite le lancé avec : ```docker run -p 8282:8282 -p 2525:2525 mock-smtp-server ```

Maintenant que le serveur est lancé il suffit juste de build et lancé le programme avec les configs indiqué.

Il faut lancer MailBot qui contient le main
