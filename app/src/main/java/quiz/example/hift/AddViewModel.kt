package quiz.example.hift

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import quiz.example.weather.db.DataBase
import quiz.example.weather.db.repository.Relis

import quiz.example.weather.model.Model


class AddViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDataBase() {
        val daoNote = DataBase.getInstance(context).getNotDao()
        REPOSITORY = Relis(daoNote)
    }

    fun insert(noteModel: Model, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insetrNote(noteModel)
            {
                onSuccess()

            }
        }

}
