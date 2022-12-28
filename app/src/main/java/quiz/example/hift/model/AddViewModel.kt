package quiz.example.hift.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import quiz.example.hift.REPOSITORY
import quiz.example.weather.model.Model


class AddViewModel : ViewModel() {

    fun insert(noteModel: Model, onSuccess:() -> Unit)=
        viewModelScope.launch  (Dispatchers.IO){
        REPOSITORY.insetrNote(noteModel){
        onSuccess()
    }

    }

}