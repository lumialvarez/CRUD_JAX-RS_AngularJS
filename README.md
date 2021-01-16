
# CRUD JAX-RS AngularJS
Ejemplo de crud usando java (JAX-RS) como backend y aplicacion en AngularJS como frontend.

Se usa una base de datos Postgres, se deja el SQL para crear la tabla:
```sql
CREATE TABLE persona
(
  dni character varying(20) NOT NULL,
  nombres character varying(255) NOT NULL,
  apellidos character varying(255) NOT NULL,
  correo character varying(255),
  fecha_nacimiento date NOT NULL,
  usuario_red character varying(50),
  fecha_baja date,
  CONSTRAINT persona_pk PRIMARY KEY (dni)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE persona
  OWNER TO postgres;
```

### Demo
![Codigo Morse](https://github.com/lumialvarez/CRUD_JAX-RS_AngularJS/blob/main/demo.jpg?raw=true)

> Written with [StackEdit](https://stackedit.io/).