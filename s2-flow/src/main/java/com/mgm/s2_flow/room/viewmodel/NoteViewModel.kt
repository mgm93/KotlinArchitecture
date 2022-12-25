package com.mgm.s2_flow.room.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.s2_flow.room.db.NoteModel
import com.mgm.s2_flow.room.repository.NoteRepository
import com.mgm.s2_flow.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 12/20/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
    val notes = MutableLiveData<MyResponse<List<NoteModel>>>()

    fun saveNote(entity:NoteModel) = viewModelScope.launch {
        repository.saveNote(entity)
    }

    fun deleteNote(entity: NoteModel) = viewModelScope.launch {
        repository.deleteNote(entity)
    }

    fun getAllNotes() = viewModelScope.launch {
        //notify loading
        notes.postValue(MyResponse.loading())
        repository.getAllNotes()
            .onEach { delay(2000) }
            .catch { e ->
                notes.postValue(MyResponse.error(e.message.toString()))
            }.collect{
                notes.postValue(MyResponse.success(it))
            }
    }
}