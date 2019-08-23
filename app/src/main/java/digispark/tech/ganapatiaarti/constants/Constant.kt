package digispark.tech.ganapatiaarti.constants

import android.content.Context

object Constant {
    val PENDING_INTENT_REQUEST_CODE = 100
    val NOTIFY_ID = 10
    val APP_NAME = "Ganpati Aarti"
    val UPDATE = "update"
    val PLAY_STORE_LINK = "https://play.google.com/store/apps/details?id=digispark.tech.ganapatiaarti"
    val OUR_APPS_LINK = "https://play.google.com/store/apps/developer?id=Digispark"
    var NOW_PLAYING_SONG_NAME = ""
    var LANGUAGE = ""

    @JvmStatic
    var LANG_CODE = "mr"

    val SHARED_PREF_NAME = "GanapatiPrefs"
    val SP_LANG_CODE = "LangCode"

    @JvmStatic
    var APP_CONTEXT: Context? = null
}
