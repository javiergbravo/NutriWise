package com.jgbravo.logger

import com.jgbravo.logger.builds.impl.ReleaseAntilog
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual object LoggerInitializer {

    actual fun init(isDebug: Boolean) {
        if (isDebug) {
            Napier.base(DebugAntilog())
        } else {
            Napier.base(ReleaseAntilog())
        }
    }
}