package utkarshsundaram.kotlinproject.persistance

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import utkarshsundaram.kotlinproject.application.MusicApplication
import utkarshsundaram.kotlinproject.model.MusicModel
import utkarshsundaram.kotlinproject.persistance.dao.MusicDao

@Database(entities = [(MusicModel::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao

    private object Holder {
        val INSTANCE = synchronized(AppDatabase::class.java) {
            Room.databaseBuilder(
                MusicApplication.instance.applicationContext,
                AppDatabase::class.java, "WynkTest"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    companion object {
        val instance: AppDatabase by lazy { Holder.INSTANCE }
    }

}