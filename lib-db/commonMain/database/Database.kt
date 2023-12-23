package app.mehmaan.db.database

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.mehmaan.core.framework.Adapter
import app.mehmaan.db.NetworkDatabase
import dev.shibasis.reaktor.framework.Feature

/*
Todo Dont attempt to use the raw driver right now
For later
1. OPFS wasm support
2. Raw SQL support

 */
abstract class Database<Controller>(
    controller: Controller,
    protected val name: String = "mehmaan.db"
): Adapter<Controller>(controller) {
    abstract suspend fun getDriver(schema: SqlSchema<QueryResult.AsyncValue<Unit>>): SqlDriver
    suspend fun getDb() = NetworkDatabase(getDriver(NetworkDatabase.Schema))

    suspend fun test() {
        val x = getDb()
        x.responseQueries
    }
}

private val databaseId = Feature.createId()
var Feature.Database: Database<*>?
    get() = fetchDependency(databaseId)
    set(database) = storeDependency(databaseId, database)