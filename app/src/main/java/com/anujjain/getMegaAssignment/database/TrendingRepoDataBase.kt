package com.anujjain.getMegaAssignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * A database that stores trending repos fetched from network.
 */
@Database(entities = [TrendingRepoDataModel::class], version = 1)
abstract class TrendingRepoDataBase : RoomDatabase() {

    //connects database to DOA, and provide interface to performing operation on database
    abstract val trendingRepoDAO : TrendingRepoDAO

    companion object{

        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         *
         * This will help us avoid repeatedly initializing the database, which is expensive.
         *
         *  The value of a volatile variable will never be cached, and all writes and
         *  reads will be done to and from the main memory. It means that changes made by one
         *  thread to shared data are visible to other threads.
         */

        @Volatile
        private var INSTANCE : TrendingRepoDataBase? = null

        /**
         * Helper function to get database
         */
        fun getInstance(context: Context) : TrendingRepoDataBase{

            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this){

                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TrendingRepoDataBase::class.java,
                        "trending_repo_database")
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this lesson. You can learn more about
                        // migration with Room in this blog post:
                        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                        .fallbackToDestructiveMigration()
                        .build()

                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                return instance
            }

        }
    }
}