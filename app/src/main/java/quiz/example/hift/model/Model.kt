package quiz.example.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "hift_table")
class Model (
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    @ColumnInfo
    var Sсheme: String = "",
    @ColumnInfo
    var type: String = "",
    @ColumnInfo
    var brand: String = "",
    @ColumnInfo
    var prepaid: String = "",
    @ColumnInfo
    var bankUrl: String = "",
    @ColumnInfo
    var bankPhone: String = "",
    @ColumnInfo
    var bank: String = "",
    @ColumnInfo
    var bankCity: String = "",
    @ColumnInfo
    var country: String = "",
    @ColumnInfo
    var cardNumber: String = "",
    @ColumnInfo
    var cardNumberLuhn: String = ""

        ) : Serializable