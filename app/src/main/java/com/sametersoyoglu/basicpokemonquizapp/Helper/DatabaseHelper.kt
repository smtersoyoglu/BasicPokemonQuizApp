package com.sametersoyoglu.basicpokemonquizapp.Helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context)
    : SQLiteOpenHelper(context,"pokemonquiz.sqlite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"pokemons\" (\n" +
                "\t\"pokemon_id\"\tINTEGER,\n" +
                "\t\"pokemon_ad\"\tTEXT,\n" +
                "\t\"pokemon_resim\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"pokemon_id AUTOINCREMENT\")\n" +
                ");")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS pokemons")
        onCreate(db)
    }
}
