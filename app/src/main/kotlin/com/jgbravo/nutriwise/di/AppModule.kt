package com.jgbravo.nutriwise.di

import com.jgbravo.nutriwise.data.di.dataModule
import com.jgbravo.nutriwise.domain.di.domainModule

val appModule = listOf(
    uiModule,
    domainModule
).plus(dataModule)