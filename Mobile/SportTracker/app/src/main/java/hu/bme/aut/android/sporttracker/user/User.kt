package hu.bme.aut.android.sporttracker.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "User")
data class User(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "mass") var mass: Int,
    @ColumnInfo(name = "height") var height: Int,
    @ColumnInfo(name = "age") var age: Int,
    @ColumnInfo(name = "sex") var sex: Int
) : Serializable