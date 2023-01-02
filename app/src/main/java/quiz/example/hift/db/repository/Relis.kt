package quiz.example.weather.db.repository

import androidx.lifecycle.LiveData
import quiz.example.weather.db.dao.NotDao
import quiz.example.weather.model.Model


class Relis(private val noteDao: NotDao):Repository{
    override val AllNotes: LiveData<List<Model>>
        get() = noteDao.getAllNotes()

    override suspend fun insetrNote(noteModel: Model, onSuccess: () -> Unit) {
        noteDao.insert(noteModel)
        onSuccess()
    }

    override suspend fun deleteNote(noteModel: Model, onSuccess: () -> Unit) {
        noteDao.delete(noteModel)
        onSuccess()
    }
}