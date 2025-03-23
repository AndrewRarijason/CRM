CREATE TABLE budget(
   budget_id INT unsigned NOT NULL AUTO_INCREMENT,
   valeur DECIMAL(10,2) NOT NULL,
   date_budget datetime NOT NULL,
   customer_id INT unsigned NOT NULL,
   PRIMARY KEY(budget_id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);


CREATE TABLE notification(
   notification_id INT unsigned NOT NULL AUTO_INCREMENT,
   message VARCHAR(250) NOT NULL,
   date_notification DATETIME NOT NULL,
   etat INT DEFAULT NULL,
   customer_id INT unsigned NOT NULL,
   PRIMARY KEY(notification_id),
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE depense(
   depense_id INT unsigned NOT NULL AUTO_INCREMENT,
   valeur_depense DECIMAL(10,2) NOT NULL,
   date_depense DATETIME NOT NULL,
   etat INT NOT NULL,
   lead_id INT unsigned DEFAULT NULL,
   ticket_id INT unsigned DEFAULT NULL,
   PRIMARY KEY(depense_id),
   FOREIGN KEY(lead_id) REFERENCES trigger_lead(lead_id),
   FOREIGN KEY(ticket_id) REFERENCES trigger_ticket(ticket_id)
);


CREATE TABLE seuil(
   seuil_id INT unsigned NOT NULL AUTO_INCREMENT,
   taux DECIMAL(10,2) NOT NULL,
   date_seuil DATETIME NOT NULL,
   PRIMARY KEY(seuil_id)
);