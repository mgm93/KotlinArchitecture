package com.mgm.s2_flow.room.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.mgm.s2_flow.room.db.NoteDao
import com.mgm.s2_flow.room.db.NoteDatabase
import com.mgm.s2_flow.room.db.NoteModel
import com.mgm.s2_flow.utils.NOTE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Majid-Golmoradi on 12/20/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object NoteModules {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()

    @Provides
    @Singleton
    fun provideNoteModel(): NoteModel = NoteModel()
}