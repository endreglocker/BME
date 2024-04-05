package hu.bme.aut.android.sporttracker.database.join_table

import androidx.room.Embedded
import androidx.room.Relation
import hu.bme.aut.android.sporttracker.sport.Sport
import hu.bme.aut.android.sporttracker.user.User
import java.io.Serializable

data class UserSport(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val sport: MutableList<Sport>
) : Serializable