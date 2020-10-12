package com.evaluation.tests

import com.evaluation.network.RestApi
import com.evaluation.search.network.AppUsersRestApiDao
import com.evaluation.search.network.AppUsersRestApiDaoImpl
import com.evaluation.tests.entity.EntityMocks
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * @author Vladyslav Havrylenko
 * @since 11.10.2020
 */
class AppUserRestApiDaoTest {

    private lateinit var appUsersRestApiDao: AppUsersRestApiDao

    @Mock
    private lateinit var appRest: RestApi

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        appUsersRestApiDao = AppUsersRestApiDaoImpl(appRest)
    }

    @Test
    fun `should do call`() {
        assertNotNull(appRest)
        assertNotNull(appUsersRestApiDao)

        val query = "IceDragonUA"
        val page = 1
        val perPage = 20

        whenever(appUsersRestApiDao.userList(query, page, perPage)).thenReturn(Single.just(EntityMocks.userList()))
        verify(appUsersRestApiDao).userList(query, page, perPage)
        verifyNoMoreInteractions(appUsersRestApiDao)
    }
}