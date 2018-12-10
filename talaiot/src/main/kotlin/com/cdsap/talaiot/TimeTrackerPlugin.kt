package com.cdsap.talaiot

import com.cdsap.talaiot.entities.TaskLength
import com.cdsap.talaiot.reporter.HttpReporter
import com.cdsap.talaiot.reporter.OutputPublisher
import org.gradle.BuildResult
import org.gradle.api.Plugin
import org.gradle.api.Project

class TimeTrackerPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        val extension: TalaiotExtension  = target.extensions.create("talaiot", TalaiotExtension::class.java, target)
        println(extension.track)
        target.gradle.addBuildListener(TaskTrackingListener(this
            ,extension))
    }

    fun onFinished(result: BuildResult, timing: MutableList<TaskLength>, talaiotExtension: TalaiotExtension) {
        val a = AggregateData(result, timing).build()
        listOf(HttpReporter(talaiotExtension),OutputPublisher())
        .forEach {
            it.send(a)
        }
    }
}