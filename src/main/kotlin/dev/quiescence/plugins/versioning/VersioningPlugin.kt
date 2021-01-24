package dev.quiescence.plugins.versioning

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

@Suppress("UNUSED")
class VersioningPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("incrementMajorVersion", IncrementMajorVersionTask::class.java)
        project.tasks.register("incrementMinorVersion", IncrementMinorVersionTask::class.java)
        project.tasks.register("incrementPatchVersion", IncrementPatchVersionTask::class.java)
    }
}

open class IncrementMajorVersionTask : DefaultTask() {
    @TaskAction
    fun incrementMajorVersion() {
        val version = project.version.toString().toVersion()
        replaceVersion(version.incrementMajor().value(), project)
    }
}

open class IncrementMinorVersionTask : DefaultTask() {
    @TaskAction
    fun incrementMajorVersion() {
        val version = project.version.toString().toVersion()
        replaceVersion(version.incrementMinor().value(), project)
    }
}

open class IncrementPatchVersionTask : DefaultTask() {
    @TaskAction
    fun incrementMajorVersion() {
        val version = project.version.toString().toVersion()
        replaceVersion(version.incrementPatch().value(), project)
    }
}

private fun replaceVersion(newVersion: String, project: Project) {
    val buildFile = project.buildFile
    val versionString = buildFile.readText().replaceFirst(project.version.toString(), newVersion)
    buildFile.writeText(versionString)
}

