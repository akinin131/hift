package quiz.example.hift

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import quiz.example.weather.db.DataBase
import quiz.example.weather.db.repository.Relis
import quiz.example.weather.model.Model


class StartViewModel(application:Application):AndroidViewModel(application) {

    val context = application


    fun initDataBase(){
        val daoNote = DataBase.getInstance(context).getNotDao()
        REPOSITORY =Relis(daoNote)
    }
    fun getAllNotes():LiveData<List<Model>>{
        return REPOSITORY.AllNotes
    }
}