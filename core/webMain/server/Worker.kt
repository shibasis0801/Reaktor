package app.mehmaan.core.cloudflare

import app.mehmaan.core.server.externals.Env
import app.mehmaan.core.server.externals.ExecutionContext
import org.w3c.fetch.Request
import org.w3c.fetch.Response
import kotlin.js.Promise

interface CloudflareWorker {
    fun fetch(request: Request, env: Env, ctx: ExecutionContext): Promise<Response>
}