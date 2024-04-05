package hu.bme.aut.android.sporttracker.sport

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.Serializable

@Entity(tableName = "Sport")
class Sport(
    @ColumnInfo(name = "sportId") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "userId") var userid: Long? = null,
    @ColumnInfo(name = "category") var category: Type,
    @ColumnInfo(name = "date") var date: Long,
    @ColumnInfo(name = "distance") var distance: Int,
    @ColumnInfo(name = "calories") var calories: Int,
) : Serializable {
    enum class Type {
        RUN, WALK, BIKING, SWM, FOOTBALL, BASKETBALL;

        companion object {
            @JvmStatic
            @TypeConverter
            fun getByOrdinal(ordinal: Int): Type? {
                var ret: Type? = null
                for (cat in values()) {
                    if (cat.ordinal == ordinal) {
                        ret = cat
                        break
                    }
                }
                return ret
            }

            @JvmStatic
            @TypeConverter
            fun toInt(category: Type): Int {
                return category.ordinal
            }
        }
    }
}