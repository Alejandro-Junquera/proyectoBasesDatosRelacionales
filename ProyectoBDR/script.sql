CREATE TABLE Alumno(
dni VARCHAR(9) NOT NULL,
nombre VARCHAR(20),
apellidos VARCHAR(50),
fecha_nacimiento DATE,
telefono int(9),
clave VARCHAR(20),
img varchar(100),
PRIMARY KEY (dni)
);

CREATE TABLE Profesor(
dni VARCHAR(9) NOT NULL,
nombre VARCHAR(20),
apellidos VARCHAR(50),
email VARCHAR(50),
clave VARCHAR(20),
img varchar(100),
PRIMARY KEY(dni)
);

CREATE TABLE Asignatura(
    id int NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    horasSemanales int(2),
    dni_pro VARCHAR(9) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (dni_pro) REFERENCES Profesor(dni)
);

CREATE TABLE RA(
    id int NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    descripcion VARCHAR(100),
    ponderacion int(2),
    id_asi int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id_asi) REFERENCES Asignatura(id)
);

CREATE TABLE Matricula(
    dni_alu VARCHAR(9) NOT NULL,
    id_asi int NOT NULL,
    PRIMARY KEY(dni_alu,id_asi),
    FOREIGN KEY (dni_alu) REFERENCES Alumno(dni),
    FOREIGN KEY (id_asi) REFERENCES Asignatura(id)
);

CREATE TABLE Califica(
    dni_alu VARCHAR(9) NOT NULL,
    id_ra int NOT NULL,
    nota float(3,2),
    PRIMARY KEY(dni_alu,id_ra),
    FOREIGN KEY(dni_alu) REFERENCES Alumno(dni),
    FOREIGN KEY (id_ra) REFERENCES RA(id)
);

INSERT INTO Alumno VALUES(
<<<<<<< HEAD
    ('76650190E', 'Javier', 'Naranjo Sanchez', '1997-03-22', 635936425, '76650190E', null),
    ('01919870Z', 'Juan', 'Gomez Perez', '1991-07-14', 689473894, '82250642J', null),
    ('49662428T', 'Julia', 'Contothanassis Marichalar', '1996-10-29', 656282930, '56348940Y', null),
    ('50466766M', 'Victoria', 'Procopio Martinez', '2001-02-11', 616751360, '50466766M', null),
    ('87480657L', 'David', 'Gnomo Feliz', '2000-02-00', 669360150, '87480657L', null)
);


INSERT INTO Profesor VALUES(
    ('10328458D', 'Raul', 'Reyes Mangano', 'raulitocai@gmail.com', '10328458D', null),
    ('20463706P', 'Felix', 'Reyes Fernandez', 'felixanchoa@gmail.com', '20463706P', null),
    ('44319524L', 'Rafael', 'Montero Gonzalez', 'rafaelito@gmail.com', '44319524L', null)
);

INSERT INTO Asignatura(nombre,horasSemanales,dni_pro) VALUES(
    ('Acceso a Datos', 6, '20463706P'),
    ('Desarrollo de interfaces', 8, '10328458D'),
    ('Programacion multimedia y de dispositivos moviles', 6, '10328458D'),
    ('Programacion de Servicios', 3, '44319524L'),
    ('Etica', 1, '44319524L')
);

=======
    ('33487471T', 'Javier', 'Naranjo Sanchez', '1997-03-22', 635936425, '76650190E', null),
    ('01919870Z', 'Juan', 'Gomez Perez', '1991-07-14', 689473894, '82250642J', null),
    ('49662428T', 'Julia', 'Contothanassis Marichalar', '1996-10-29', 656282930, '56348940Y', null),
    ('50466766M', 'Victoria', 'Procopio Martinez', '2001-02-11', 616751360, '50466766M', null),
    ('87480657L', 'David', 'Gnomo Feliz', '2000-02-00', 669360150, '87480657L', null)
);


INSERT INTO Profesor VALUES(
    ('10328458D', 'Raul', 'Reyes Mangano', 'raulitocai@gmail.com', '10328458D', null),
    ('20463706P', 'Felix', 'Reyes Fernandez', 'felixanchoa@gmail.com', '20463706P', null),
    ('44319524L', 'Rafael', 'Montero Gonzalez', 'rafaelito@gmail.com', '44319524L', null)
);

INSERT INTO Asignatura(nombre,horasSemanales,dni_pro) VALUES(
    ('Acceso a Datos', 6, '20463706P'),
    ('Desarrollo de interfaces', 8, '10328458D'),
    ('Programacion multimedia y de dispositivos moviles', 6, '10328458D'),
    ('Programacion de Servicios', 3, '44319524L'),
    ('Etica', 1, '44319524L')
);

CREATE TABLE RA(
    id int NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    descripcion VARCHAR(100),
    ponderacion FLOAT(2),
    id_asi int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id_asi) REFERENCES Asignatura(id)
);
>>>>>>> branch 'master' of https://github.com/Alejandro-Junquera/ProyectoBDR.git

INSERT INTO RA(nombre,descripcion,ponderacion,id_asi) VALUES(
    ('RA1', 'Parte 1 AD', 5, ),
    (),
    (),
    (),
    (),
    (),
    (),
    (),
    ()
);