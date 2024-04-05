package hu.bme.aut.android.sporttracker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hu.bme.aut.android.sporttracker.database.join_table.UserSport
import hu.bme.aut.android.sporttracker.sport.Sport
import hu.bme.aut.android.sporttracker.user.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM User")
    fun getAllUserSport(): List<UserSport>

    @Query("SELECT sport.* FROM Sport ")
    fun getAllSport(): MutableList<Sport>

    @Insert
    fun insertUser(user: User) : Long

    @Insert
    fun insertSport(sport: Sport) : Long

    @Delete
    fun deleteUser(user: User)

    @Delete
    fun deleteSport(sport: Sport)

    @Update
    fun updateUser(user: User)

    @Update
    fun updateSport(sport: Sport)
}