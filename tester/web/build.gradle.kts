import groovy.json.JsonSlurper

fun Exec.yarn(command: String) {
    commandLine("yarn.cmd $command".split(" "))
}

fun Exec.npx(command: String) {
    commandLine("npx.cmd $command".split(" "))
}

fun Exec.cmd(command: String) {
     commandLine("powershell /c $command".split(" "))
}

val dev by tasks.registering(Exec::class) {
    group = "web"
    cmd("npx kill-port 3000")
    dependsOn(":core:jsBrowserProductionLibraryDistribution")
    cmd("rm yarn.lock")
    cmd("yarn")
    val packageJson = JsonSlurper().parseText(file("package.json").readText()) as Map<String, Map<String, String>>
    val scripts = packageJson["scripts"]
    val dev = scripts?.get("dev")
    yarn("dev")
}

