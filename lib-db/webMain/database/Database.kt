package app.mehmaan.db.database

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import kotlinx.browser.window
import org.w3c.dom.Worker

class WebDatabase: Database<Unit>(Unit) {
    override suspend fun getDriver(schema: SqlSchema<QueryResult.AsyncValue<Unit>>): SqlDriver {
        return WebWorkerDriver(
                Worker(
                    js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""") as String
                )
        ).also { schema.create(it).await() }
    }
}