CREATE SCHEMA IF NOT EXISTS `polizasdb`;
USE `polizasdb`;

CREATE TABLE IF NOT EXISTS `polizasdb`.`provincia` (
  `idprovincia` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcionprovincia` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idprovincia`));

INSERT INTO provincia (descripcionprovincia) VALUES ("Buenos Aires");
INSERT INTO provincia (descripcionprovincia) VALUES ("Catamarca");
INSERT INTO provincia (descripcionprovincia) VALUES ("Chaco");
INSERT INTO provincia (descripcionprovincia) VALUES ("Chubut");
INSERT INTO provincia (descripcionprovincia) VALUES ("Córdoba");
INSERT INTO provincia (descripcionprovincia) VALUES ("Corrientes");
INSERT INTO provincia (descripcionprovincia) VALUES ("Entre Ríos");
INSERT INTO provincia (descripcionprovincia) VALUES ("Formosa");
INSERT INTO provincia (descripcionprovincia) VALUES ("Jujuy");
INSERT INTO provincia (descripcionprovincia) VALUES ("La Pampa");
INSERT INTO provincia (descripcionprovincia) VALUES ("La Rioja");
INSERT INTO provincia (descripcionprovincia) VALUES ("Mendoza");
INSERT INTO provincia (descripcionprovincia) VALUES ("Misiones");
INSERT INTO provincia (descripcionprovincia) VALUES ("Neuquén");
INSERT INTO provincia (descripcionprovincia) VALUES ("Río Negro");
INSERT INTO provincia (descripcionprovincia) VALUES ("Salta");
INSERT INTO provincia (descripcionprovincia) VALUES ("San Juan");
INSERT INTO provincia (descripcionprovincia) VALUES ("San Luis");
INSERT INTO provincia (descripcionprovincia) VALUES ("Santa Cruz");
INSERT INTO provincia (descripcionprovincia) VALUES ("Santa Fe");
INSERT INTO provincia (descripcionprovincia) VALUES ("Santiago del Estero");
INSERT INTO provincia (descripcionprovincia) VALUES ("Tierra del Fuego, Antártida e Islas del Atlántico Sur");
INSERT INTO provincia (descripcionprovincia) VALUES ("Tucumán");

CREATE TABLE IF NOT EXISTS `polizasdb`.`localidad` (
  `idLocalidad` INT(10) NOT NULL AUTO_INCREMENT,
  `descripcionlocalidad` VARCHAR(50) NOT NULL,
  `codigopostal` VARCHAR(10) NOT NULL,
  `provincia_id` INT(11) NOT NULL,
  PRIMARY KEY (`idLocalidad`),
  FOREIGN KEY (`provincia_id`) REFERENCES `polizasdb`.`provincia` (`idprovincia`));

-- Saque todo de https://codigo-postal.co/argentina/

INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("La Plata", "1900" , 1);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("La Mascota", "1900" , 1);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("San Fernando del Valle de Catamarca", "4700", 2);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Resistencia", "3500", 3);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Rawson", "9103", 4);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Córdoba", "5000", 5);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Corrientes", "3400", 6);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Paraná","3100", 7);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Formosa","3600", 8);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("San Salvador de Jujuy","4600", 9);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Santa Rosa","6300", 10);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("La Rioja","5300", 11);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Mendoza","5500", 12);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Posadas","3300", 13);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Neuquén","8300", 14);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Viedma","8500", 15);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Salta","4400", 16);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("San Juan","5400", 17);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("San Luis","5700", 18);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Río Gallegos","9400", 19);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Santa Fe","3000", 20);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Santiago del Estero","4200", 21);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Ushuaia","9410", 22);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("San Miguel de Tucumán", "4000", 23);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Lules", "4128", 23);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Trancas", "4124", 23);
INSERT INTO localidad (descripcionlocalidad, codigopostal, provincia_id) VALUES ("Aguilares", "4152", 23);

CREATE TABLE IF NOT EXISTS `polizasdb`.`persona` (
  `idpersona` INT NOT NULL AUTO_INCREMENT,
  `localidad_id` INT(11) NOT NULL,
  `nombres` VARCHAR(50) NOT NULL,
  `apellidos` VARCHAR(50) NOT NULL,
  `fechanacimiento` DATE NOT NULL,
  `tipodocumento` ENUM('CUIT', 'CEDULA', 'DNI', 'LIBRETA_ENROLE', 'LIBRETA_CIVICA', 'PASAPORTE') NOT NULL,
  `documento` VARCHAR(20) NOT NULL,
  `domicilio` VARCHAR(100) NOT NULL,
  `correo` VARCHAR(255) NOT NULL,
  `telefono` VARCHAR(255) NOT NULL,
  `sexo` TINYINT NOT NULL,
  `contraseña` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE INDEX `correo_UNIQUE` (`correo`),
  FOREIGN KEY (`localidad_id`) REFERENCES `polizasdb`.`localidad` (`idlocalidad`));

INSERT INTO persona (localidad_id, nombres, apellidos, fechanacimiento, tipodocumento, documento, domicilio, correo, telefono, sexo, contraseña) 
VALUES (23, "Nicolas", "Ramirez", '1998-11-01', 'DNI', "2324323", "Cordoba 797", "colquenicolas1976@gmail.com", "3814778827", true, "12345678");
INSERT INTO persona (localidad_id, nombres, apellidos, fechanacimiento, tipodocumento, documento, domicilio, correo, telefono, sexo, contraseña) 
VALUES (12,"Juan Maria", "Lopez", '1999-02-22', 'CEDULA', "46453533", "Pasaje lol 323", "juanmlopez@gmail.com", "3815244560", true, "juanlopez");
INSERT INTO persona (localidad_id, nombres, apellidos, fechanacimiento, tipodocumento, documento, domicilio, correo, telefono, sexo, contraseña) 
VALUES (24,"Cornelius", "Robinson", '1990-07-10', 'CUIT', "123456780", "calle falsa 123", "robinsoncor@gmail.com", "3813425621", true, "pedropica");
INSERT INTO persona (localidad_id, nombres, apellidos, fechanacimiento, tipodocumento, documento, domicilio, correo, telefono, sexo, contraseña) 
VALUES (10,"Beatriz", "Ilaisa", '1980-12-15', 'PASAPORTE', "87654322", "cualquiera :v", "beatrizelos@gmail.com", "3811122445", false, "aycomopican");
INSERT INTO persona (localidad_id, nombres, apellidos, fechanacimiento, tipodocumento, documento, domicilio, correo, telefono, sexo, contraseña) 
VALUES (26,"Johnny", "Bravo", '1995-02-14', 'LIBRETA_CIVICA', "73763845", "call me 555", "jbravo@gmail.com", "3814101025", true, "555913henshin");
INSERT INTO persona (localidad_id, nombres, apellidos, fechanacimiento, tipodocumento, documento, domicilio, correo, telefono, sexo, contraseña) 
VALUES (7,"Yeni", "Gonzalez", '2001-08-08', 'LIBRETA_ENROLE', "26565334", "villa numero 20", "yenigonzalez@gmail.com", "3814727362", false, "no se me ocurre nada");

CREATE TABLE IF NOT EXISTS `polizasdb`.`usuario` (
  `legajo` VARCHAR(50) NOT NULL,
  `persona_id` INT NOT NULL,
  `estado` TINYINT NOT NULL,
  `tipousuario` ENUM('ADMINISTRADOR', 'VENDEDOR', 'PERITO', 'GESTOR_DE_SINIESTROS') NOT NULL,
  PRIMARY KEY (`legajo`),
  UNIQUE INDEX `legajo_UNIQUE` (`legajo`),
  FOREIGN KEY (`persona_id`) REFERENCES `polizasdb`.`persona` (`idpersona`));

INSERT INTO usuario (legajo, persona_id, estado, tipousuario) VALUES ("50001", 1, true, 'ADMINISTRADOR');
INSERT INTO usuario (legajo, persona_id, estado, tipousuario) VALUES ("50002", 2, true, 'VENDEDOR');
INSERT INTO usuario (legajo, persona_id, estado, tipousuario) VALUES ("50003", 3, true, 'PERITO');
INSERT INTO usuario (legajo, persona_id, estado, tipousuario) VALUES ("50004", 4, true, 'GESTOR_DE_SINIESTROS');

CREATE TABLE IF NOT EXISTS `polizasdb`.`cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `persona_id` INT NOT NULL,
  PRIMARY KEY (`idcliente`),
  FOREIGN KEY (`persona_id`) REFERENCES `polizasdb`.`Persona` (`idpersona`));

INSERT INTO cliente (persona_id) VALUES (5);
INSERT INTO cliente (persona_id) VALUES (6);
INSERT INTO cliente (persona_id) VALUES (1);
INSERT INTO cliente (persona_id) VALUES (2);
INSERT INTO cliente (persona_id) VALUES (3);
INSERT INTO cliente (persona_id) VALUES (4);

CREATE TABLE IF NOT EXISTS `polizasdb`.`marca` (
  `idmarca` INT(11) NOT NULL AUTO_INCREMENT,
  `nombremarca` VARCHAR(50) NOT NULL,
  `descripcionmarca` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idmarca`));

INSERT INTO marca (nombremarca, descripcionmarca) VALUES ("Ford", "Es una marca alemana");
INSERT INTO marca (nombremarca, descripcionmarca) VALUES ("Volkswagen", "Es una marca reconocida");
INSERT INTO marca (nombremarca, descripcionmarca) VALUES ("Chevrolet", "Es una marca muy reconocida :v");

CREATE TABLE IF NOT EXISTS `polizasdb`.`modelo` (
  `idmodelo` INT(10) NOT NULL AUTO_INCREMENT,
  `nombremodelo` VARCHAR(50) NOT NULL,
  `descripcionmodelo` VARCHAR(50) NOT NULL,
  `marca_id` INT(11) NOT NULL,
  PRIMARY KEY (`idmodelo`),
  FOREIGN KEY (`marca_id`) REFERENCES `polizasdb`.`marca` (`idmarca`));
  
INSERT INTO modelo (nombremodelo, descripcionmodelo, marca_id) VALUES ("Kuga", "Es una modelo piola", 1);
INSERT INTO modelo (nombremodelo, descripcionmodelo, marca_id) VALUES ("Mondeo", "Es una modelo genial", 1);
INSERT INTO modelo (nombremodelo, descripcionmodelo, marca_id) VALUES ("Ecosport", "Es una modelo cool :v", 1);
INSERT INTO modelo (nombremodelo, descripcionmodelo, marca_id) VALUES ("Golf", "Es una modelo piola 2", 2);
INSERT INTO modelo (nombremodelo, descripcionmodelo, marca_id) VALUES ("Polo", "Es una modelo cool 2 :v", 2);
INSERT INTO modelo (nombremodelo, descripcionmodelo, marca_id) VALUES ("Camaro", "Es una modelo asombroso", 3);
INSERT INTO modelo (nombremodelo, descripcionmodelo, marca_id) VALUES ("Cruce", "Es una modelo cosmico -.-", 3);
INSERT INTO modelo (nombremodelo, descripcionmodelo, marca_id) VALUES ("Onix", "Es una modelo logico (wp)", 3);
INSERT INTO modelo (nombremodelo, descripcionmodelo, marca_id) VALUES ("S-10", "Es una modelo cool de nuevo", 3);

CREATE TABLE IF NOT EXISTS `polizasdb`.`version` (
  `idversion` INT(10) NOT NULL AUTO_INCREMENT,
  `nombreversion` VARCHAR(50) NOT NULL,
  `descripcionversion` VARCHAR(50) NOT NULL,
  `preciomercado` DOUBLE(10,2) NOT NULL,
  `preciomercadognc` DOUBLE(10,2) NOT NULL,
  `modelo_id` INT(10) NOT NULL,
  PRIMARY KEY (`idversion`),
  FOREIGN KEY (`modelo_id`) REFERENCES `polizasdb`.`modelo` (`idmodelo`));
  
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("1.2", "La mejor gama ahrrex", 		5000000.50, 5500000.50, 1);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("1.4", "La mejor gama ahrrex 2", 	6000000.50, 6600000.50, 1);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("1.2", "odio escribir", 			7300000.25, 7800000.10, 2);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("1.4", "Las descripciones matan", 	1500000.75, 2200000.20, 3);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("1.8", "Las descripciones matan 2", 2000000.75, 2400000.20, 3);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("2.0", "nadie me lee", 				1200000.75, 1500000.60, 4);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("3.2", "puras descripciones", 		2200000.25, 2500000.90, 5);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("3.4", "puras descripciones 2", 	2400000.25, 2700000.90, 5);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("Gama 1", "etc. etc. etcetera!", 	3300000.80, 3700000.25, 6);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("Gama 2", "lol deja de leerme", 	1800000.30, 2100000.75, 7);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("3.0", "apruebenos profe", 			2000000.40, 2200000.40, 8);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("3.1", "apruebenos profe 2", 		2200000.40, 2400000.40, 8);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("5.1", "Legalicen a Colque :v", 	2300000.20, 2800000.50, 9);
INSERT INTO version (nombreversion, descripcionversion, preciomercado, preciomercadognc, modelo_id) VALUES ("5.2", "Legalicen a Colque 2 :v", 	2700000.20, 3000000.50, 9);

CREATE TABLE IF NOT EXISTS `polizasdb`.`configuracionlocalidad` (
  `idconfiglocalidad` INT(11) NOT NULL AUTO_INCREMENT,
  `localidad_id` INT(11) NOT NULL,
  `nombrecl` VARCHAR(50) NOT NULL,
  `descuentocl` INT(3),
  `gananciacl` INT(3),
  `recargocl` DOUBLE(10,2),
  `activocl` TINYINT NOT NULL,
  PRIMARY KEY (`idconfiglocalidad`),
  FOREIGN KEY (`localidad_id`) REFERENCES `polizasdb`.`localidad` (`idlocalidad`));
  
INSERT INTO configuracionlocalidad (localidad_id, nombrecl, descuentocl, gananciacl, recargocl, activocl) VALUES (24, "config Localidad 1", 0, 7, 0, true);
INSERT INTO configuracionlocalidad (localidad_id, nombrecl, descuentocl, gananciacl, recargocl, activocl) VALUES (25, "config Localidad 2", 8, 0, 0, true);
INSERT INTO configuracionlocalidad (localidad_id, nombrecl, descuentocl, gananciacl, recargocl, activocl) VALUES (9, "config Localidad 3", 0, 0, 130, true);
INSERT INTO configuracionlocalidad (localidad_id, nombrecl, descuentocl, gananciacl, recargocl, activocl) VALUES (21, "config Localidad 4", 10, 0, 0, true);
INSERT INTO configuracionlocalidad (localidad_id, nombrecl, descuentocl, gananciacl, recargocl, activocl) VALUES (13, "config Localidad 5", 0, 15, 0, true);
INSERT INTO configuracionlocalidad (localidad_id, nombrecl, descuentocl, gananciacl, recargocl, activocl) VALUES (26, "config Localidad 6", 0, 0, 300, true);

CREATE TABLE IF NOT EXISTS `polizasdb`.`configuracionedad` (
  `idconfigedad` INT(11) NOT NULL AUTO_INCREMENT,
  `nombrece` VARCHAR(50) NOT NULL,
  `minimace` INT(11) NOT NULL,
  `maximace` INT(11) NOT NULL,
  `descuentoce` INT(3),
  `gananciace` INT(3),
  `recargoce` DOUBLE(10,2),
  `activoce` TINYINT NOT NULL,
  PRIMARY KEY (`idconfigedad`));

INSERT INTO configuracionedad (nombrece, minimace, maximace, descuentoce, gananciace, recargoce, activoce) VALUES ("Ganacia de los Jovenes", 18, 23, 0, 15, 0, true);
INSERT INTO configuracionedad (nombrece, minimace, maximace, descuentoce, gananciace, recargoce, activoce) VALUES ("Recargo para Adultos", 24, 50, 0, 0, 120.33, true);
INSERT INTO configuracionedad (nombrece, minimace, maximace, descuentoce, gananciace, recargoce, activoce) VALUES ("Descuento para Ancianos", 51, 75, 10, 0, 0, true);

CREATE TABLE IF NOT EXISTS `polizasdb`.`configuracionantiguedad` (
  `idconfigantiguedad` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreca` VARCHAR(50) NOT NULL,
  `minimaca` INT(11) NOT NULL,
  `maximaca` INT(11) NOT NULL,
  `descuentoca` INT(3),
  `gananciaca` INT(3),
  `recargoca` DOUBLE(10,2),
  `activoca` TINYINT NOT NULL,
  PRIMARY KEY (`idconfigantiguedad`));

INSERT INTO configuracionantiguedad (nombreca, minimaca, maximaca, descuentoca, gananciaca, recargoca, activoca) VALUES ("Cuando el Auto es nuevo", 0, 3, 10, 0, 0, true);
INSERT INTO configuracionantiguedad (nombreca, minimaca, maximaca, descuentoca, gananciaca, recargoca, activoca) VALUES ("Cuando es usado", 4, 6, 0, 0, 100, true);
INSERT INTO configuracionantiguedad (nombreca, minimaca, maximaca, descuentoca, gananciaca, recargoca, activoca) VALUES ("Cuando es viejo", 7, 10, 0, 5, 0, true);
INSERT INTO configuracionantiguedad (nombreca, minimaca, maximaca, descuentoca, gananciaca, recargoca, activoca) VALUES ("Cuando es antiguito", 11, 20, 0, 12, 0, true);

CREATE TABLE IF NOT EXISTS `polizasdb`.`detalle` (
  `iddetalle` INT(11) NOT NULL AUTO_INCREMENT,
  `nombredetalle` VARCHAR(50) NOT NULL,
  `descripciondetalle` VARCHAR(100) NOT NULL,
  `porcentajemiles` INT(3) NOT NULL,
  `montofijo` DOUBLE(10,2) NOT NULL,
  `activodetalle` TINYINT NOT NULL,
  PRIMARY KEY (`iddetalle`));

INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Responsable Civil hasta $23.000.000", "Te cubrimos hsta 23millones de pesos", 1, 23000000.00, true);
INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Destrucción, Robo/Hurto e Incendio Total", "Te cubrimos si te roban, destruyen o incendian el vehiculo", 1, 0, true);
INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Robo/Hurto e Incendio Parcial", "Te cubrimos si te roban o incendian el vehiculo parcialmente", 2, 0, true);
INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Daños por intento de robo", "Te cubrimos los daños del vehiculo si te roban", 2, 0, true);
INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Reposicion a nuevo de neumáticos.", "Te cubrimos la reposicion de las llantas de tu vehiculo", 1, 0, true);
INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Extensión de cobertura de paises Limitrofes", "Te cubrimos los daños, de tu vehiculo en paises limitrofes con Argentina", 2, 0, true);
INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Cristales", "Te cubrimos la destrucción de los cristales de tu vehiculo", 1, 0, true);
INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Daños Parciales", "Creo que no hace falta explicar", 1, 0, true);
INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Asistencia Standard", "Te la debo :v", 1, 0, true);
INSERT INTO detalle (nombredetalle, descripciondetalle, porcentajemiles, montofijo, activodetalle) VALUES ("Asistencia Go!", "Te la debo 2 :v", 2, 0, true);

CREATE TABLE IF NOT EXISTS `polizasdb`.`cobertura` (
  `idcobertura` INT(11) NOT NULL AUTO_INCREMENT,
  `nombrecobertura` VARCHAR(50) NOT NULL,
  `descripcioncobertura` VARCHAR(100) NOT NULL,
  `recargoporatraso` INT(3) NOT NULL,
  `activocobertura` TINYINT NOT NULL,
  PRIMARY KEY (`idcobertura`));

INSERT INTO cobertura (nombrecobertura, descripcioncobertura, recargoporatraso, activocobertura) VALUES ("Terceros Completo", "Es Buena", 2, true);
INSERT INTO cobertura (nombrecobertura, descripcioncobertura, recargoporatraso, activocobertura) VALUES ("Terceros Completo Versión Premium", "Es Excelente", 4, true);
INSERT INTO cobertura (nombrecobertura, descripcioncobertura, recargoporatraso, activocobertura) VALUES ("Todo Riesgo", "Es La Mejor Sin Duda", 7, true);

CREATE TABLE IF NOT EXISTS `polizasdb`.`coberturadetalle` (
  `idcoberturadetalle` INT(11) NOT NULL AUTO_INCREMENT,
  `cobertura_id` INT(11) NOT NULL,
  `detalle_id` INT(11) NOT NULL,
  PRIMARY KEY (`idcoberturadetalle`),
  FOREIGN KEY (`cobertura_id`) REFERENCES `polizasdb`.`cobertura` (`idcobertura`),
  FOREIGN KEY (`detalle_id`) REFERENCES `polizasdb`.`detalle` (`iddetalle`));

INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (1, 1);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (1, 2);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (1, 3);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (1, 4);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (1, 5);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (2, 1);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (2, 2);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (2, 3);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (2, 4);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (2, 5);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (2, 6);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (2, 7);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 1);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 2);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 3);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 4);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 5);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 6);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 7);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 8);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 9);
INSERT INTO coberturadetalle (cobertura_id, detalle_id) VALUES (3, 10);

CREATE TABLE IF NOT EXISTS `polizasdb`.`tipocontratacion` (
  `idtipocontratacion` INT(50) NOT NULL AUTO_INCREMENT,
  `nombrecontratacion` VARCHAR(50) NOT NULL,
  `cantidadmesescontratacion` INT(11) NOT NULL,
  PRIMARY KEY (`idtipocontratacion`));

INSERT INTO tipocontratacion (nombrecontratacion, cantidadmesescontratacion) VALUES ("Por 6 Meses", 6);
INSERT INTO tipocontratacion (nombrecontratacion, cantidadmesescontratacion) VALUES ("Por 1 Año", 12);
INSERT INTO tipocontratacion (nombrecontratacion, cantidadmesescontratacion) VALUES ("Por 2 Año", 24);
INSERT INTO tipocontratacion (nombrecontratacion, cantidadmesescontratacion) VALUES ("Por 3 Año", 36);

CREATE TABLE IF NOT EXISTS `polizasdb`.`periodopago` (
  `idperiodopago` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreperiodopago` VARCHAR(50) NOT NULL,
  `cantidadmesespago` INT(11) NOT NULL,
  `descuentoperiodopago` INT(11) NOT NULL,
  PRIMARY KEY (`idperiodopago`));

INSERT INTO periodopago (nombreperiodopago, cantidadmesespago, descuentoperiodopago) VALUES ("Mensual", 1, 0);
INSERT INTO periodopago (nombreperiodopago, cantidadmesespago, descuentoperiodopago) VALUES ("Bimestral", 2, 3);
INSERT INTO periodopago (nombreperiodopago, cantidadmesespago, descuentoperiodopago) VALUES ("Trimestral", 3, 5);
INSERT INTO periodopago (nombreperiodopago, cantidadmesespago, descuentoperiodopago) VALUES ("Semestral", 6, 7);
INSERT INTO periodopago (nombreperiodopago, cantidadmesespago, descuentoperiodopago) VALUES ("Anual", 12, 10);

CREATE TABLE IF NOT EXISTS `polizasdb`.`vehiculo` (
  `idvehiculo` INT(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` INT(11) NOT NULL,
  `version_id` INT(11) NOT NULL,
  `matricula` VARCHAR(50) NOT NULL,
  `añofabricación` INT(10) NOT NULL,
  `numeromotor` VARCHAR(20) NOT NULL,
  `chasis` VARCHAR(45) NOT NULL,
  `gnc` TINYINT NOT NULL,
  PRIMARY KEY (`idvehiculo`),
  FOREIGN KEY (`cliente_id`) REFERENCES `polizasdb`.`cliente` (`idcliente`),
  FOREIGN KEY (`version_id`) REFERENCES `polizasdb`.`version` (`idversion`));

INSERT INTO vehiculo (cliente_id, version_id, matricula, añofabricación, numeromotor, chasis, gnc) VALUES (1, 10, "2548 LIO", 2013, "3646322", "chasis 1", true);
INSERT INTO vehiculo (cliente_id, version_id, matricula, añofabricación, numeromotor, chasis, gnc) VALUES (1, 4, "3569 YTR", 1998, "4568573", "chasis 2", false);
INSERT INTO vehiculo (cliente_id, version_id, matricula, añofabricación, numeromotor, chasis, gnc) VALUES (2, 7, "4578 JUH", 2015, "5737822", "chasis 3", false);

CREATE TABLE IF NOT EXISTS `polizasdb`.`cotizacion` (
  `idcotizacion` INT(11) NOT NULL AUTO_INCREMENT,
  `vehiculo_id` INT(11) NOT NULL,
  `configlocalidad_id` INT(11),
  `configedad_id` INT(11),
  `configantiguedad_id` INT(11),
  `fechacreacioncotizacion` DATE NOT NULL,
  `fechavencimientocotizacion` DATE NOT NULL,
  PRIMARY KEY (`idcotizacion`),
  FOREIGN KEY (`vehiculo_id`) REFERENCES `polizasdb`.`vehiculo` (`idvehiculo`),
  FOREIGN KEY (`configlocalidad_id`) REFERENCES `polizasdb`.`configuracionLocalidad` (`idconfiglocalidad`),
  FOREIGN KEY (`configedad_id`) REFERENCES `polizasdb`.`configuracionEdad` (`idconfigedad`),
  FOREIGN KEY (`configantiguedad_id`) REFERENCES `polizasdb`.`configuracionAntiguedad` (`idconfigantiguedad`));

INSERT INTO cotizacion (vehiculo_id, configlocalidad_id, configedad_id, configantiguedad_id, fechacreacioncotizacion, fechavencimientocotizacion) VALUES (1, 6, 2, 3, '2022-10-04', '2022-11-03');
INSERT INTO cotizacion (vehiculo_id, configlocalidad_id, configedad_id, configantiguedad_id, fechacreacioncotizacion, fechavencimientocotizacion) VALUES (2, 6, 2, 4, '2022-10-10', '2022-11-09');
INSERT INTO cotizacion (vehiculo_id, configlocalidad_id, configedad_id, configantiguedad_id, fechacreacioncotizacion, fechavencimientocotizacion) VALUES (2, 6, 2, 4, '2022-10-15', '2022-11-14');
INSERT INTO cotizacion (vehiculo_id, configlocalidad_id, configedad_id, configantiguedad_id, fechacreacioncotizacion, fechavencimientocotizacion) VALUES (3, null, 1, 3, '2022-09-16', '2022-10-15');

CREATE TABLE IF NOT EXISTS `polizasdb`.`lineacotizacion` (
  `idlineacotizacion` INT(11) NOT NULL AUTO_INCREMENT,
  `monto` DOUBLE(10,2) NOT NULL,
  `cotizacion_id` INT(11) NOT NULL,
  `cobertura_id` INT(11) NOT NULL,
  PRIMARY KEY (`idlineacotizacion`),
  FOREIGN KEY (`cotizacion_id`) REFERENCES `polizasdb`.`cotizacion` (`idcotizacion`),
  FOREIGN KEY (`cobertura_id`) REFERENCES `polizasdb`.`cobertura` (`idcobertura`));

INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (1963.83, 1, 1);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (2625.33, 1, 2);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (3507.33, 1, 3);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (1596.33, 2, 1);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (2100.33, 2, 2);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (2772.33, 2, 3);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (1596.33, 3, 1);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (2100.33, 3, 2);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (2772.33, 3, 3);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (1859.55, 4, 1);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (2656.50, 4, 2);
INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) VALUES (3719.10, 4, 3);

CREATE TABLE IF NOT EXISTS `polizasdb`.`documentacion` (
  `iddocumentacion` INT(11) NOT NULL AUTO_INCREMENT,
  `fotofrontal` LONGBLOB NOT NULL,
  `fototrasera` LONGBLOB NOT NULL,
  `fotolateraluno` LONGBLOB NOT NULL,
  `fotolateraldos` LONGBLOB NOT NULL,
  `fototecho` LONGBLOB NOT NULL,
  `cedulaverde` LONGBLOB NOT NULL,
  PRIMARY KEY (`iddocumentacion`));

INSERT INTO documentacion (fotofrontal, fototrasera, fotolateraluno, fotolateraldos, fototecho, cedulaverde) VALUES (LOAD_FILE('C:/Docs/Pol_1/P1_FF.jpg'), LOAD_FILE('C:/Docs/Pol_1/P1_FTR.jpg'), LOAD_FILE('C:/Docs/Pol_1/P1_FL1.jpg'), LOAD_FILE('C:/Docs/Pol_1/P1_FL2.jpg'), LOAD_FILE('C:/Docs/Pol_1/P1_FT.jpg'), LOAD_FILE('C:/Docs/Pol_1/P1_CV.jpg'));
INSERT INTO documentacion (fotofrontal, fototrasera, fotolateraluno, fotolateraldos, fototecho, cedulaverde) VALUES (LOAD_FILE('C:/Docs/Pol_2/P2_FF.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_FTR.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_FL1.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_FL2.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_FT.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_CV.jpg'));
INSERT INTO documentacion (fotofrontal, fototrasera, fotolateraluno, fotolateraldos, fototecho, cedulaverde) VALUES (LOAD_FILE('C:/Docs/Pol_2/P2_FF.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_FTR.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_FL1.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_FL2.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_FT.jpg'), LOAD_FILE('C:/Docs/Pol_2/P2_CV.jpg'));
INSERT INTO documentacion (fotofrontal, fototrasera, fotolateraluno, fotolateraldos, fototecho, cedulaverde) VALUES (LOAD_FILE('C:/Docs/Pol_3/P3_FF.jpg'), LOAD_FILE('C:/Docs/Pol_3/P3_FTR.jpg'), LOAD_FILE('C:/Docs/Pol_3/P3_FL1.jpg'), LOAD_FILE('C:/Docs/Pol_3/P3_FL2.jpg'), LOAD_FILE('C:/Docs/Pol_3/P3_FT.jpg'), LOAD_FILE('C:/Docs/Pol_3/P3_CV.jpg'));

CREATE TABLE IF NOT EXISTS `polizasdb`.`poliza` (
  `numeropoliza` INT(11) NOT NULL AUTO_INCREMENT,
  `usuario_legajo` VARCHAR(50),
  `documentacion_id` INT(11) NOT NULL,
  `lineacotizacion_id` INT(11) NOT NULL,
  `periodopago_id` INT(11),
  `tipocontratacion_id` INT(11),
  `preciopolizaactual` DOUBLE(10,2) NOT NULL,
  `montoasegurado` DOUBLE(10,2) NOT NULL,
  `fec_cont_poliza` DATE,
  `hora_cont_poliza` TIME,
  `fec_venc_poliza` DATE,
  `fec_canc_poliza` DATE,
  `auto_renov_poliza` TINYINT NOT NULL,
  `estadopoliza` ENUM('PENDIENTE', 'EN_REVISIÓN', 'RECHAZADA', 'APROBADA', 'VIGENTE', 'IMPAGA', 'VENCIDA', 'CANCELADA') NOT NULL,
  PRIMARY KEY (`numeropoliza`),
  FOREIGN KEY (`usuario_legajo`) REFERENCES `polizasdb`.`usuario` (`legajo`),
  FOREIGN KEY (`documentacion_id`) REFERENCES `polizasdb`.`documentacion` (`iddocumentacion`),
  FOREIGN KEY (`lineacotizacion_id`) REFERENCES `polizasdb`.`lineacotizacion` (`idlineacotizacion`),
  FOREIGN KEY (`periodopago_id`) REFERENCES `polizasdb`.`periodopago` (`idperiodopago`),
  FOREIGN KEY (`tipocontratacion_id`) REFERENCES `polizasdb`.`tipocontratacion` (`idtipocontratacion`));

INSERT INTO poliza (usuario_legajo, documentacion_id, lineacotizacion_id, periodopago_id, tipocontratacion_id, preciopolizaactual, montoasegurado, fec_cont_poliza, hora_cont_poliza, fec_venc_poliza, fec_canc_poliza, auto_renov_poliza, estadopoliza) 
VALUES (50002, 1, 2, null, null, 2625.33, 2100000.75, null, null, null, null, false, 'APROBADA');
INSERT INTO poliza (usuario_legajo, documentacion_id, lineacotizacion_id, periodopago_id, tipocontratacion_id, preciopolizaactual, montoasegurado, fec_cont_poliza, hora_cont_poliza, fec_venc_poliza, fec_canc_poliza, auto_renov_poliza, estadopoliza) 
VALUES (50002, 2, 5, null, null, 2100.33, 1500000.75, null, null, null, null, false, 'RECHAZADA');
INSERT INTO poliza (usuario_legajo, documentacion_id, lineacotizacion_id, periodopago_id, tipocontratacion_id, preciopolizaactual, montoasegurado, fec_cont_poliza, hora_cont_poliza, fec_venc_poliza, fec_canc_poliza, auto_renov_poliza, estadopoliza) 
VALUES (null, 3, 7, null, null, 2100.33, 1500000.75, null, null, null, null, false, 'PENDIENTE');

CREATE TABLE IF NOT EXISTS `polizasdb`.`pago` (
  `idpago` INT(11) NOT NULL AUTO_INCREMENT,
  `poliza_num` INT(11) NOT NULL,
  `totalpago` DOUBLE(10,2) NOT NULL,
  `fechapago` DATE NOT NULL,
  `horapago` TIME NOT NULL,
  PRIMARY KEY (`idpago`),
  FOREIGN KEY (`poliza_num`) REFERENCES `polizasdb`.`poliza` (`numeropoliza`));

CREATE TABLE IF NOT EXISTS `polizasdb`.`revision` (
  `idrevision` INT(11) NOT NULL AUTO_INCREMENT,
  `poliza_num` INT(11) NOT NULL,
  `usuario_legajo` VARCHAR(50),
  `fecharevision` DATE,
  `horarevsion` TIME,
  PRIMARY KEY (`idrevision`),
  FOREIGN KEY (`poliza_num`) REFERENCES `polizasdb`.`poliza` (`numeropoliza`),
  FOREIGN KEY (`usuario_legajo`) REFERENCES `polizasdb`.`Usuario` (`legajo`));
  
CREATE TABLE IF NOT EXISTS `polizasdb`.`siniestro` (
  `idsiniestro` INT(11) NOT NULL AUTO_INCREMENT,
  `poliza_num` INT(11) NOT NULL,
  `usuario_legajo` VARCHAR(50),
  `fechasiniestro` DATE NOT NULL,
  `horasiniestro` TIME NOT NULL,
  `fotodenuncia` LONGBLOB NOT NULL,
  `fotovehiculo` LONGBLOB NOT NULL,
  `estadosiniestro` ENUM('PENDIENTE', 'RECHAZADA', 'APROBADA') NOT NULL,
  PRIMARY KEY (`idsiniestro`),
  FOREIGN KEY (`poliza_num`) REFERENCES `polizasdb`.`poliza` (`numeropoliza`),
  FOREIGN KEY (`usuario_legajo`) REFERENCES `polizasdb`.`Usuario` (`legajo`));

-- use polizasdb;

-- SELECT * FROM persona p where p.idpersona=7;
-- SELECT * FROM vehiculo v where v.idvehiculo=4;
-- SELECT * FROM vehiculo v, cotizacion c where v.idvehiculo=c.vehiculo_id and v.idvehiculo=4;
-- SELECT * FROM cotizacion c, lineacotizacion l where c.idcotizacion = l.cotizacion_id and c.vehiculo_id=4;
-- SELECT * FROM documentacion d WHERE d.iddocumentacion=4;
-- SELECT * FROM poliza p, documentacion d where p.documentacion_id = d.iddocumentacion and d.iddocumentacion=4;