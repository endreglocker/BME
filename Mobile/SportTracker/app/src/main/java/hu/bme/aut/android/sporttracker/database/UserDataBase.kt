package hu.bme.aut.android.sporttracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.bme.aut.android.sporttracker.sport.Sport
import hu.bme.aut.android.sporttracker.user.User

@Database(entities = [User::class, Sport::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        fun getDatabase(applicationContext: Context): UserDataBase {
            return Room.databaseBuilder(
                applicationContext,
                UserDataBase::class.java,
                "user-sport-database"
            ).build()
        }
    }
}