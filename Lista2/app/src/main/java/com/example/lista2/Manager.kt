package com.example.lista2
import android.content.Context
import android.content.SharedPreferences

class Manager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun addUser(user: User): Boolean
    {
        if(sharedPreferences.contains(user.username))
        {
            return false;
        }
        val editor = sharedPreferences.edit()
        editor.putString(user.username, user.password)
        editor.apply()
        return true;
    }

    fun addUser(username: String, pass: String): Boolean
    {
        if(sharedPreferences.contains(username))
        {
            return false;
        }
        val editor = sharedPreferences.edit()
        editor.putString(username, pass)
        editor.apply()
        return true;
    }

    fun getUser(login: String, pass: String): Boolean {
        if (login.isBlank() || pass.isBlank()) return false
        val userPass = sharedPreferences.getString(login, null)
        return userPass == pass
    }


}