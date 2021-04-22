package com.anujjain.getMegaAssignment

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.anujjain.getMegaAssignment.database.BuiltBy
import com.anujjain.getMegaAssignment.database.TrendingRepoDAO
import com.anujjain.getMegaAssignment.database.TrendingRepoDataBase
import com.anujjain.getMegaAssignment.database.TrendingRepoDataModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * This class test database related related operation,
 * does not include all the test cases,
 *
 * But, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */
@RunWith(AndroidJUnit4::class)
class TrendingRepoDataBaseTest {


    private lateinit var trendingRepoDAO: TrendingRepoDAO
    private lateinit var db: TrendingRepoDataBase


    @Before
    fun createDB() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(appContext, TrendingRepoDataBase::class.java)
            .allowMainThreadQueries()
            .build()

        trendingRepoDAO = db.trendingRepoDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertList() {
        val listInserted = createDummyList()
        trendingRepoDAO.insertRepoList(listInserted)
        val listRetrieved = trendingRepoDAO.getRepos()

        assert(listInserted.size == listRetrieved.value?.size)
        assert(listInserted[0].url == listRetrieved.value?.get(0)?.url)
        assert(listInserted[0].getDesc() == listRetrieved.value?.get(0)?.getDesc())
    }

    private fun createDummyList(): List<TrendingRepoDataModel> {
        val repo1 = TrendingRepoDataModel(
            author = "xingshaocheng",
            name = "architect-awesome",
            avatar = "https://github.com/xingshaocheng.png",
            url = "https://github.com/xingshaocheng/architect-awesome",
            description = "后端架构师技术图谱",
            language = "Go",
            languageColor = "#3572A5",
            stars = 7333,
            forks = 1546,
            currentPeriodStars = 1528
        )
        return listOf(repo1)
    }
}