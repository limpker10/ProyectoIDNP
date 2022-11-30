package com.example.myapplication.database;

public class HistorialTabla {
    public static String onCreate(){
        String query = "CREATE TABLE \"HistorialRecoleccion\" (\n" +
                "\t\"Historial_id\"\tINTEGER NOT NULL,\n" +
                "\t\"User_id\"\tINTEGER NOT NULL,\n" +
                "\t\"Tipo_id\"\tINTEGER NOT NULL,\n" +
                "\t\"Lugar\"\tINTEGER,\n" +
                "\t\"CodigoPlastico\"\tTEXT NOT NULL UNIQUE,\n" +
                "\t\"Imagen\"\tBLOB,\n" +
                "\t\"Cantidad\"\tINTEGER NOT NULL,\n" +
                "\t\"Estado\"\tINTEGER NOT NULL DEFAULT 1,\n" +
                "\tFOREIGN KEY(\"Tipo_id\") REFERENCES \"Plastico\"(\"Plastico_id\"),\n" +
                "\tFOREIGN KEY(\"User_id\") REFERENCES \"Usuario\"(\"User_id\"),\n" +
                "\tPRIMARY KEY(\"Historial_id\" AUTOINCREMENT)\n" +
                ");";
        return query;
    }
    public static String TableName(){
        return "HistorialRecoleccion";
    }
}
