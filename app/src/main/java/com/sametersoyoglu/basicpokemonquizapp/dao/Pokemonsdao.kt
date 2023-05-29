package com.sametersoyoglu.basicpokemonquizapp.dao

import com.sametersoyoglu.basicpokemonquizapp.Helper.DatabaseHelper

class Pokemonsdao {

    fun random5Pokemon(vt: DatabaseHelper) : ArrayList<Pokemons> {
        val pokemonsList = ArrayList<Pokemons>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM pokemons ORDER BY RANDOM() LIMIT 10",null)

        while (c.moveToNext()) {
            val pokemon_id = c.getColumnIndexOrThrow("pokemon_id")
            val pokemon_ad = c.getColumnIndexOrThrow("pokemon_ad")
            val pokemon_resim = c.getColumnIndexOrThrow("pokemon_resim")

            val pokemon = Pokemons(
                c.getInt(pokemon_id),
                c.getString(pokemon_ad),
                c.getString(pokemon_resim)
            )

            pokemonsList.add(pokemon)
        }


        return pokemonsList
    }


    fun random3FalsePokemon(vt: DatabaseHelper, pokemon_id: Int) : ArrayList<Pokemons> {
        val pokemonsList = ArrayList<Pokemons>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM pokemons WHERE pokemon_id != $pokemon_id ORDER BY RANDOM() LIMIT 3",null)


        while (c.moveToNext()) {
            val pokemon_id = c.getColumnIndexOrThrow("pokemon_id")
            val pokemon_ad = c.getColumnIndexOrThrow("pokemon_ad")
            val pokemon_resim = c.getColumnIndexOrThrow("pokemon_resim")

            val pokemon = Pokemons(
                c.getInt(pokemon_id),
                c.getString(pokemon_ad),
                c.getString(pokemon_resim)
            )

            pokemonsList.add(pokemon)
        }


        return pokemonsList
    }

}