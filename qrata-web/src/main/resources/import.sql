INSERT INTO roles (id, date_created, last_updated, status, name) VALUES (NEXTVAL('roles_id_seq'), now(), now(), 1, 'ADMIN');
INSERT INTO roles (id, date_created, last_updated, status, name) VALUES (NEXTVAL('roles_id_seq'), now(), now(), 1, 'EDITOR');
INSERT INTO roles (id, date_created, last_updated, status, name) VALUES (NEXTVAL('roles_id_seq'), now(), now(), 1, 'EXPERT');
INSERT INTO "user" (id, date_created, last_updated, status, password, username, role_id) VALUES (NEXTVAL('user_id_seq'), now(), now(), 1 , 'password', 'admin',1);
INSERT INTO userinfo (id, date_created, last_updated, status, email, firstname, lastname, user_id) VALUES (NEXTVAL('userinfo_id_seq'), now(), now(), 1, 'admin@qrata.com', 'Admin', 'User', 1);

INSERT INTO categories VALUES 
(1,NULL,NULL,NULL,1,'1234564',1,'Body & Soul',NULL),
(2,NULL,NULL,NULL,1,'45488',1,'Life Stages',NULL),
(3,NULL,'2019-07-25 14:34:17','2019-07-25 14:34:17',1,'213',1,'Good Life',NULL),
(4,NULL,'2019-07-26 11:53:01','2019-07-26 11:53:01',1,'456',1,'Home Life',3),
(5,NULL,'2019-07-26 18:18:49','2019-07-26 18:18:49',1,'658',1,'Technology',3),
(6,NULL,'2019-07-26 18:19:40','2019-07-26 18:19:40',1,'486',1,'Sports, Games & Cars',3),
(7,NULL,'2019-07-26 18:20:53','2019-07-26 18:20:53',1,'6589',1,'Money & Business',3),
(8,NULL,'2019-07-26 18:21:54',NULL,1,'965',2,'Learn How & Why',2),
(111,NULL,NULL,NULL,NULL,NULL,NULL,'World',111),
(112,NULL,NULL,NULL,NULL,NULL,NULL,'Countries & Cultures',111),
(113,NULL,NULL,NULL,NULL,NULL,NULL,'Africa',112),
(114,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Africa',113),
(115,NULL,NULL,NULL,NULL,NULL,NULL,'Famous People - Africa',113),
(117,NULL,NULL,NULL,NULL,NULL,NULL,'African Resources',113),(118,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Africa',113),
(119,NULL,NULL,NULL,NULL,NULL,NULL,'Australia',112),(122,NULL,NULL,NULL,NULL,NULL,NULL,'Geography & Landmarks - Austrailia',119),(123,NULL,NULL,NULL,NULL,NULL,NULL,'History - General',119),(124,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Australia',119),(125,NULL,NULL,NULL,NULL,NULL,NULL,'Central America',112),(128,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Central America',125),(130,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Central America',125),(131,NULL,NULL,NULL,NULL,NULL,NULL,'Asia (Central)',112),(132,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Central Asia',131),(134,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Central Asia',131),(137,NULL,NULL,NULL,NULL,NULL,NULL,'Asia (East) - General',112),(143,NULL,NULL,NULL,NULL,NULL,NULL,'Europe',112),(144,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Europe General',143),(146,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Europe',143),(149,NULL,NULL,NULL,NULL,NULL,NULL,'Travel Resources',112),(155,NULL,NULL,NULL,NULL,NULL,NULL,'Middle East',112),(156,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Middle East',155),(157,NULL,NULL,NULL,NULL,NULL,NULL,'Famous People - Middle East',155),(158,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Middle East (1)',155),(159,NULL,NULL,NULL,NULL,NULL,NULL,'History - Middle East (1)',155),(160,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Middle East',155),(167,NULL,NULL,NULL,NULL,NULL,NULL,'Pacific (North)',112),(168,NULL,NULL,NULL,NULL,NULL,NULL,'Culture - Pacific General',167),(170,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - South Pacific (1)',167),(172,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Hawaii',167),(173,NULL,NULL,NULL,NULL,NULL,NULL,'Famous People',111),(174,NULL,NULL,NULL,NULL,NULL,NULL,'Arts',173),(177,NULL,NULL,NULL,NULL,NULL,NULL,'Painting',174),(178,NULL,NULL,NULL,NULL,NULL,NULL,'Photography - General',174),(180,NULL,NULL,NULL,NULL,NULL,NULL,'Business',173),(185,NULL,NULL,NULL,NULL,NULL,NULL,'Media',180),(186,NULL,NULL,NULL,NULL,NULL,NULL,'Entertainment',173),(187,NULL,NULL,NULL,NULL,NULL,NULL,'Movies - General',186),(188,NULL,NULL,NULL,NULL,NULL,NULL,'Music - General',186),(191,NULL,NULL,NULL,NULL,NULL,NULL,'TV',186),(192,NULL,NULL,NULL,NULL,NULL,NULL,'General',173),(193,NULL,NULL,NULL,NULL,NULL,NULL,'African-Americans',192),(194,NULL,NULL,NULL,NULL,NULL,NULL,'Asian-Americans',192),(195,NULL,NULL,NULL,NULL,NULL,NULL,'Education',192),(196,NULL,NULL,NULL,NULL,NULL,NULL,'European-Americans',192),(198,NULL,NULL,NULL,NULL,NULL,NULL,'Gossip',192),(199,NULL,NULL,NULL,NULL,NULL,NULL,'Hispanic-Americans',192),(200,NULL,NULL,NULL,NULL,NULL,NULL,'Law',192),(202,NULL,NULL,NULL,NULL,NULL,NULL,'Native Americans',192),(203,NULL,NULL,NULL,NULL,NULL,NULL,'History',173),(204,NULL,NULL,NULL,NULL,NULL,NULL,'Ancient - Famous People',203),(205,NULL,NULL,NULL,NULL,NULL,NULL,'Medieval - Famous People',203),(206,NULL,NULL,NULL,NULL,NULL,NULL,'Renaissance - Famous People',203),(207,NULL,NULL,NULL,NULL,NULL,NULL,'Industrial Era - Famous People',203),(208,NULL,NULL,NULL,NULL,NULL,NULL,'Modern - Famous People',203),(209,NULL,NULL,NULL,NULL,NULL,NULL,'Inventors',173),(210,NULL,NULL,NULL,NULL,NULL,NULL,'Communications',209),(211,NULL,NULL,NULL,NULL,NULL,NULL,'Electronics',209),(212,NULL,NULL,NULL,NULL,NULL,NULL,'Energy',209),(213,NULL,NULL,NULL,NULL,NULL,NULL,'Information Technology',209),(214,NULL,NULL,NULL,NULL,NULL,NULL,'Manufacturing',209),(215,NULL,NULL,NULL,NULL,NULL,NULL,'Materials',209),(216,NULL,NULL,NULL,NULL,NULL,NULL,'Transportation',209),(217,NULL,NULL,NULL,NULL,NULL,NULL,'Politics & Government & Philosophy',173),(218,NULL,NULL,NULL,NULL,NULL,NULL,'Leaders - Country - General',217),(219,NULL,NULL,NULL,NULL,NULL,NULL,'Leaders - Historical - General',217),(221,NULL,NULL,NULL,NULL,NULL,NULL,'Leaders - Global - General',217),(222,NULL,NULL,NULL,NULL,NULL,NULL,'Leaders - US',217),(223,NULL,NULL,NULL,NULL,NULL,NULL,'Science',173),(224,NULL,NULL,NULL,NULL,NULL,NULL,'Astronomy',223),(226,NULL,NULL,NULL,NULL,NULL,NULL,'Chemistry',223),(228,NULL,NULL,NULL,NULL,NULL,NULL,'Physics - General',223),(230,NULL,NULL,NULL,NULL,NULL,NULL,'Sports',173),(236,NULL,NULL,NULL,NULL,NULL,NULL,'Olympics - Famous People',230),(239,NULL,NULL,NULL,NULL,NULL,NULL,'Tennis - Players',230),(240,NULL,NULL,NULL,NULL,NULL,NULL,'Women',173),(241,NULL,NULL,NULL,NULL,NULL,NULL,'Arts - Women',240),(242,NULL,NULL,NULL,NULL,NULL,NULL,'Business - Women',240),(244,NULL,NULL,NULL,NULL,NULL,NULL,'Historical - Women',240),(246,NULL,NULL,NULL,NULL,NULL,NULL,'Science - Women',240),(248,NULL,NULL,NULL,NULL,NULL,NULL,'Geography & Landmarks',111),(249,NULL,NULL,NULL,NULL,NULL,NULL,'Africa',248),(256,NULL,NULL,NULL,NULL,NULL,NULL,'Asia',248),(262,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks - Asia',256),(263,NULL,NULL,NULL,NULL,NULL,NULL,'Australia & Antarctica',248),(270,NULL,NULL,NULL,NULL,NULL,NULL,'Canada',248),(271,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Agricultural',270),(272,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Canadian Cities',270),(273,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Climate',270),(274,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Country',270),(275,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Geological',270),(277,NULL,NULL,NULL,NULL,NULL,NULL,'Central & S. America',248),(284,NULL,NULL,NULL,NULL,NULL,NULL,'Europe',248),(291,NULL,NULL,NULL,NULL,NULL,NULL,'Middle East',248),(293,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Middle East Cities',291),(295,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Country',291),(297,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks & Geography - Middle East (2)',291),(298,NULL,NULL,NULL,NULL,NULL,NULL,'Oceans & Seas',248),(305,NULL,NULL,NULL,NULL,NULL,NULL,'Pacific Islands',248),(312,NULL,NULL,NULL,NULL,NULL,NULL,'United States',248),(314,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - US Cities',312),(318,NULL,NULL,NULL,NULL,NULL,NULL,'Landmarks - US',312),(319,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - US States',312),(321,NULL,NULL,NULL,NULL,NULL,NULL,'History',111),(322,NULL,NULL,NULL,NULL,NULL,NULL,'Africa',321),(323,NULL,NULL,NULL,NULL,NULL,NULL,'Africa - Central',322),(324,NULL,NULL,NULL,NULL,NULL,NULL,'History - Cultural - Africa',322),(325,NULL,NULL,NULL,NULL,NULL,NULL,'History - Economic',322),(326,NULL,NULL,NULL,NULL,NULL,NULL,'African Resources',322),(327,NULL,NULL,NULL,NULL,NULL,NULL,'History - African Military & Political',322),
(328,NULL,NULL,NULL,NULL,NULL,NULL,'Africa - Northern',322),(329,NULL,NULL,NULL,NULL,NULL,NULL,'Africa - Southern',322),(330,NULL,NULL,NULL,NULL,NULL,NULL,'Asia',321),(331,NULL,NULL,NULL,NULL,NULL,NULL,'History - China',330),(332,NULL,NULL,NULL,NULL,NULL,NULL,'Cultural History - Asia',330),(336,NULL,NULL,NULL,NULL,NULL,NULL,'History - India',330),(340,NULL,NULL,NULL,NULL,NULL,NULL,'Australia',321),(346,NULL,NULL,NULL,NULL,NULL,NULL,'Canada',321),(347,NULL,NULL,NULL,NULL,NULL,NULL,'Cultural History - Canada',346),(348,NULL,NULL,NULL,NULL,NULL,NULL,'Economic History - Canada',346),(349,NULL,NULL,NULL,NULL,NULL,NULL,'History - Canada (1)',346),(351,NULL,NULL,NULL,NULL,NULL,NULL,'History - Ontario Canada',346),(352,NULL,NULL,NULL,NULL,NULL,NULL,'History - Other Provinces',346),(353,NULL,NULL,NULL,NULL,NULL,NULL,'History - Quebec',346),(354,NULL,NULL,NULL,NULL,NULL,NULL,'Central America',321),(357,NULL,NULL,NULL,NULL,NULL,NULL,'Cultural History - Central America',354),(358,NULL,NULL,NULL,NULL,NULL,NULL,'Economic History - Central America',354),(361,NULL,NULL,NULL,NULL,NULL,NULL,'History - Central American Military & Political',354),(363,NULL,NULL,NULL,NULL,NULL,NULL,'Europe',321),(366,NULL,NULL,NULL,NULL,NULL,NULL,'Economic History - Europe',363),(369,NULL,NULL,NULL,NULL,NULL,NULL,'History - European (2)',363),(371,NULL,NULL,NULL,NULL,NULL,NULL,'History - European Military & Political',363),(373,NULL,NULL,NULL,NULL,NULL,NULL,'Middle East',321),(374,NULL,NULL,NULL,NULL,NULL,NULL,'Cultural History - Middle East',373),(376,NULL,NULL,NULL,NULL,NULL,NULL,'History - Egypt',373),(377,NULL,NULL,NULL,NULL,NULL,NULL,'History - Middle East (2)',373),(378,NULL,NULL,NULL,NULL,NULL,NULL,'History - Israel',373),(379,NULL,NULL,NULL,NULL,NULL,NULL,'History - Middle East Military & Political',373),(380,NULL,NULL,NULL,NULL,NULL,NULL,'History - Other Middle East Countries',373),(381,NULL,NULL,NULL,NULL,NULL,NULL,'Religion - Middle East History',373),(382,NULL,NULL,NULL,NULL,NULL,NULL,'Pacific Islands',321),(385,NULL,NULL,NULL,NULL,NULL,NULL,'History - Hawaii General',382),(388,NULL,NULL,NULL,NULL,NULL,NULL,'World History',321),(389,NULL,NULL,NULL,NULL,NULL,NULL,'Paleontology & Archaeology',388),(390,NULL,NULL,NULL,NULL,NULL,NULL,'Civilization',388),(201631,NULL,NULL,NULL,NULL,NULL,NULL,'Prehistory - Paleolithic',277),(393,NULL,NULL,NULL,NULL,NULL,NULL,'Evolution - Human',388),(397,NULL,NULL,NULL,NULL,NULL,NULL,'Travel',111),(398,NULL,NULL,NULL,NULL,NULL,NULL,'Accommodations',397),(399,NULL,NULL,NULL,NULL,NULL,NULL,'Bed & Breakfast',398),(400,NULL,NULL,NULL,NULL,NULL,NULL,'Home Exchanges',398),(401,NULL,NULL,NULL,NULL,NULL,NULL,'Hotel Discounters',398),(402,NULL,NULL,NULL,NULL,NULL,NULL,'Hotels/Resorts - Luxury',398),(403,NULL,NULL,NULL,NULL,NULL,NULL,'Apartments, Villas, Cottages',398),(404,NULL,NULL,NULL,NULL,NULL,NULL,'Agents, Reservations & Tours',397),(405,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Business (2)',404),(406,NULL,NULL,NULL,NULL,NULL,NULL,'Tours - Culture & Science',404),(408,NULL,NULL,NULL,NULL,NULL,NULL,'Tours - Family Vacations',404),(409,NULL,NULL,NULL,NULL,NULL,NULL,'US Government Travel Alerts',404),(410,NULL,NULL,NULL,NULL,NULL,NULL,'Tours - Nature',404),(411,NULL,NULL,NULL,NULL,NULL,NULL,'Auctions - Travel',404),(412,NULL,NULL,NULL,NULL,NULL,NULL,'Tours - Specialized Packages',404),
(413,NULL,NULL,NULL,NULL,NULL,NULL,'Air Travel, Activities & Adventures',397),
(414,NULL,NULL,NULL,NULL,NULL,NULL,'Alerts - Automatic (Air Travel)',413),(415,NULL,NULL,NULL,NULL,NULL,NULL,'Airline Rules',413),(419,NULL,NULL,NULL,NULL,NULL,NULL,'Travel - Last Minute',413),(421,NULL,NULL,NULL,NULL,NULL,NULL,'Travelers Assistance, Insurance, & Complaints',397),(423,NULL,NULL,NULL,NULL,NULL,NULL,'Complaints - General',421),(426,NULL,NULL,NULL,NULL,NULL,NULL,'Insurance - Travel (1)',421),(427,NULL,NULL,NULL,NULL,NULL,NULL,'Complaints - Travel & Vacations',421),(428,NULL,NULL,NULL,NULL,NULL,NULL,'City Transit',397),(429,NULL,NULL,NULL,NULL,NULL,NULL,'Auto Rentals',428),(430,NULL,NULL,NULL,NULL,NULL,NULL,'Maps - Bus & Subway Guides',428),(431,NULL,NULL,NULL,NULL,NULL,NULL,'City Guides - General',428),(435,NULL,NULL,NULL,NULL,NULL,NULL,'Embassies & Tourism Offices',397),(436,NULL,NULL,NULL,NULL,NULL,NULL,'Foreign Consulates (1)',435),(437,NULL,NULL,NULL,NULL,NULL,NULL,'Customs Requirements',435),(438,NULL,NULL,NULL,NULL,NULL,NULL,'US Embassies - Foreign',435),(439,NULL,NULL,NULL,NULL,NULL,NULL,'Foreign Consulates (2)',435),(440,NULL,NULL,NULL,NULL,NULL,NULL,'Passports & Visas (2)',435),(442,NULL,NULL,NULL,NULL,NULL,NULL,'US Tourism Office',435),(443,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks & Travelogues',397),(444,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - Asia',443),(445,NULL,NULL,NULL,NULL,NULL,NULL,'Customized Trips',443),(446,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - Europe (1)',443),(447,NULL,NULL,NULL,NULL,NULL,NULL,'Events & Festivals',443),(448,NULL,NULL,NULL,NULL,NULL,NULL,'Museums - General (2)',443),(450,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - Travel Tips (1)',443),(451,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - Tropical Islands',443),(452,NULL,NULL,NULL,NULL,NULL,NULL,'Guidebooks - United States',443),(453,NULL,NULL,NULL,NULL,NULL,NULL,'Health & Safety',397),(454,NULL,NULL,NULL,NULL,NULL,NULL,'Airline Safety',453),(456,NULL,NULL,NULL,NULL,NULL,NULL,'Health Advice - Foreign',453);

ALTER SEQUENCE categories_id_seq RESTART WITH 457;
