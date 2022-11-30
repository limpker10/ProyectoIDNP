package com.example.myapplication.database;

public class PlascticoTabla {
    public static String onCreate(){
        String query = "CREATE TABLE \"Plastico\" (\n" +
                "\t\"Plastico_id\"\tINTEGER NOT NULL,\n" +
                "\t\"Nombre\"\tTEXT,\n" +
                "\t\"EscalaR\"\tINTEGER NOT NULL,\n" +
                "\t\"Estado\"\tINTEGER NOT NULL DEFAULT 1,\n" +
                "\tPRIMARY KEY(\"Plastico_id\")\n" +
                ");";
        return query;
    }
    public static String TableName(){
        return "Plastico";
    }
}
