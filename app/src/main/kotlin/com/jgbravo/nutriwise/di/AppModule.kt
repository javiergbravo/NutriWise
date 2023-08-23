package com.jgbravo.nutriwise.di

import com.jgbravo.nutriwise.data.di.dataModule
import com.jgbravo.nutriwise.domain.di.domainModule
import com.jgbravo.nutriwise.ui.di.uiModule

val appModule = uiModule.plus(domainModule).plus(dataModule)