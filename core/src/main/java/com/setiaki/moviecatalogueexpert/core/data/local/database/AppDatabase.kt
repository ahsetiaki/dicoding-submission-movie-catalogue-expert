package com.setiaki.moviecatalogueexpert.core.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.setiaki.moviecatalogueexpert.core.data.local.dao.*
import com.setiaki.moviecatalogueexpert.core.data.local.entity.*
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory


@Database(
    entities = [
        MovieEntity::class,
        TvShowEntity::class,
        GenreEntity::class,
        MovieGenreCrossRefEntity::class,
        TvShowGenreCrossRefEntity::class,
        MoviePageEntity::class,
        TvShowPageEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun genreDao(): GenreDao
    abstract fun movieGenreCrossRefDao(): MovieGenreCrossRefDao
    abstract fun tvShowGenreCrossRefDao(): TvShowGenreCrossRefDao
    abstract fun moviePageDao(): MoviePageDao
    abstract fun tvShowPageDao(): TvShowPageDao

    companion object {
        private const val DATABASE_NAME = "MovieCatalogueExpert.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            val passphrase: ByteArray = SQLiteDatabase.getBytes("setiaki".toCharArray())
            val factory = SupportFactory(passphrase)

            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .build()
        }
    }
}