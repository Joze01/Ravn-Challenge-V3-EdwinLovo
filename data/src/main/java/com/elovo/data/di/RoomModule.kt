package com.elovo.data.di

import android.content.Context
import androidx.room.Room
import com.elovo.data.db.RavnDB
import com.elovo.data.db.RavnDB.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRavnDB(@ApplicationContext context: Context): RavnDB =
        Room.databaseBuilder(
            context.applicationContext,
            RavnDB::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun personDaoProvider(db: RavnDB) = db.personDao()
}
