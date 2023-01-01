package quiz.example.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import quiz.example.weather.model.Model


@Dao
interface NotDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(noteModel: Model)

    @Delete
    fun delete(noteModel: Model)

    @Query("SELECT *from hift_table")
    fun getAllNotes(): LiveData<List<Model>>
}