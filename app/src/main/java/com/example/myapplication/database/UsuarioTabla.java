package com.example.myapplication.database;

public class UsuarioTabla {

    public static final String NOMBRE_TABLA="Usuario";
    public static final String USUARIO_ID="id";
    public static final String USUARIO_NOMBRE="Nombre";
    public static final String USUARIO_APELLIDO="Apellido";
    public static final String USUARIO_DNI="DNI";
    public static final String USUARIO_CONTACTO="Contacto";
    public static final String USUARIO_EMAIL="Email";
    public static final String USUARIO_ESTADO="Estado";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+
            NOMBRE_TABLA+" ("+USUARIO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USUARIO_NOMBRE+" TEXT NOT NULL,"+
            USUARIO_APELLIDO+" TEXT,"+
            USUARIO_DNI+" INTEGER UNIQUE,"+
            USUARIO_CONTACTO+" TEXT,"+
            USUARIO_EMAIL+" TEXT NOT NULL,"+
            USUARIO_ESTADO+" INTEGER NOT NULL DEFAULT 1)";

    public static String onCreate(){

        String query = "CREATE TABLE \"Usuario\" (\n" +
                "            \"User_id\"\tINTEGER NOT NULL,\n" +
                "            \"Nombre\"\tTEXT NOT NULL,\n" +
                "            \"Apellido\"\tTEXT,\n" +
                "            \"DNI\"\tINTEGER,\n" +
                "            \"Contacto\"\tTEXT,\n" +
                "            \"Email\"\tTEXT NOT NULL,\n" +
                "            \"Estado\"\tINTEGER NOT NULL DEFAULT 1,\n" +
                "    UNIQUE(\"DNI\"),\n" +
                "    PRIMARY KEY(\"User_id\" AUTOINCREMENT)";
        return query;
    }
    public static String TableName(){
        return "Usuario";
    }
}
