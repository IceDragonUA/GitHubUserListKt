package com.evaluation.tests.dao.network

import com.evaluation.details.network.AppUserDetailRestApiDao
import com.evaluation.details.network.AppUserDetailRestApiDaoImpl
import com.evaluation.executor.TestExecutor
import com.evaluation.network.RestApi
import com.evaluation.tests.dao.RetrofitMocks
import com.evaluation.tests.test
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * @author Vladyslav Havrylenko
 * @since 11.10.2020
 */
class AppUserDetailsRestApiDaoTest {

    private lateinit var appUserDetailRestApiDao: AppUserDetailRestApiDao

    private var appRest: RestApi = RetrofitMocks.appRest

    @Mock
    private lateinit var executor: TestExecutor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        appUserDetailRestApiDao = AppUserDetailRestApiDaoImpl(appRest, executor)
        whenever(executor.mainExecutor).thenReturn(Schedulers.trampoline())
        whenever(executor.postExecutor).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `should do call`() {
        assertNotNull(appRest)
        assertNotNull(executor)
        assertNotNull(appUserDetailRestApiDao)

        val query = "IceDragonUA"

        appUserDetailRestApiDao.userDetails(query).test {
            assertValueCount(1)
            assertNoErrors()
            assertComplete()
        }
    }


}