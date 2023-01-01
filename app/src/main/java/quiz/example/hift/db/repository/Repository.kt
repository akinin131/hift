package quiz.example.weather.db.repository

import androidx.lifecycle.LiveData
import quiz.example.weather.model.Model


interface Repository {
    val AllNotes: LiveData<List<Model>>
    suspend fun insetrNote(noteModel: Model, onSuccess: () -> Unit)
    suspend fun deleteNote(noteModel: Model, onSuccess: () -> Unit)
}