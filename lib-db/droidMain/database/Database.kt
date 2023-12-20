package app.mehmaan.db.database

import androidx.activity.ComponentActivity
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

class AndroidDatabase(activity: ComponentActivity): Database<ComponentActivity>(activity) {
    override suspend fun getDriver(schema: SqlSchema<QueryResult.AsyncValue<Unit>>): SqlDriver {
        val activity = controller ?: throw NullPointerException("Needs an Activity instance")
        return AndroidSqliteDriver(schema.synchronous(), activity, name)
    }
}


