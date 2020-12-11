package com.example.ArnauINC.db;

import android.provider.BaseColumns;

public class IncidenciaContract {
    public static abstract class IncidenciaEntry implements BaseColumns{
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "db";
        public static final String TABLE_NAME="Incidencia";
        public static final String ID="id";
        public static final String TABLE_NAME_TITLE="title";
        public static final String TABLE_NAME_PRIORITY="priority";
        public static final String TABLE_NAME_DATE = "date";
        public static final String TABLE_NAME_DESCRIPTION = "description";
        public static final String TABLE_NAME_STATE = "state";
    }
}
