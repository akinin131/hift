package quiz.example.weather.db



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import quiz.example.weather.db.dao.NotDao
import quiz.example.weather.model.Model



@Database(entities = [Model::class],version = 1)
abstract class NotDataBase: RoomDatabase() {
    abstract fun getNotDao(): NotDao

    companion object {
        private var database: NotDataBase? = null


       @Synchronized
       fun getInstance(context:Context):NotDataBase{

           return if(database==null){
               database = Room.databaseBuilder(context,NotDataBase::class.java,"db").build()
               database as NotDataBase
           }else{
               database as NotDataBase
           }
       }
    }
}






