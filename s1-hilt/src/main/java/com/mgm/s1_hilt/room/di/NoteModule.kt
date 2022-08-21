package com.mgm.s1_hilt.room.di

import android.content.Context
import androidx.room.Room
import com.mgm.s1_hilt.NOTE_DATABASE
import com.mgm.s1_hilt.room.db.NoteDao
import com.mgm.s1_hilt.room.db.NoteDatabase
import com.mgm.s1_hilt.room.db.NoteModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Majid-Golmoradi on 8/21/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context):NoteDatabase =
        Room.databaseBuilder(context,NoteDatabase::class.java, NOTE_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideNoteDao(db:NoteDatabase):NoteDao = db.noteDao()

    @Provides
    @Singleton
    fun provideNoteEntity():NoteModel= NoteModel()

}