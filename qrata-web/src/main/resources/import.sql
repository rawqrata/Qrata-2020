INSERT INTO roles (id, date_created, last_updated, status, name) VALUES (NEXTVAL('roles_id_seq'), now(), now(), 1, 'ADMIN');
INSERT INTO roles (id, date_created, last_updated, status, name) VALUES (NEXTVAL('roles_id_seq'), now(), now(), 1, 'EDITOR');
INSERT INTO roles (id, date_created, last_updated, status, name) VALUES (NEXTVAL('roles_id_seq'), now(), now(), 1, 'EXPERT');
INSERT INTO "user" (id, date_created, last_updated, status, password, username, role_id) VALUES (NEXTVAL('user_id_seq'), now(), now(), 1 , 'password', 'admin',1);
INSERT INTO userinfo (id, date_created, last_updated, status, email, firstname, lastname, user_id) VALUES (NEXTVAL('userinfo_id_seq'), now(), now(), 1, 'admin@qrata.com', 'Admin', 'User', 1);