package org.example

import com.google.caliper.{Runner => CaliperRunner}
import com.olegych.ClassForNameTest
import java.io.File

object Runner {

  // main method for IDEs, from the CLI you can also run the caliper Runner directly
  // or simply use SBTs "run" action
  def main(args: Array[String]) {
    // we simply pass in the CLI args,
    // we could of course also just pass hardcoded arguments to the caliper Runner
    //    CaliperRunner.main(classOf[EATest], args +к+ Array(/*"--measureMemory",*/"-JEA=-XX:+DoEscapeAnalysis,-XX:-DoEscapeAnalysis", "-Jserver=-server"))
    val vms = List(None, Some("d:\\Distrib\\Coding\\Java\\jdk\\7_13_x64\\bin\\java.exe"))
    vms.foreach {
      vm =>
        val params = Nil ++ args ++
          List("--captureVmLog", "--saveResults", new File("results.json").getAbsolutePath, "-Jserver=-server") ++
          vm.toSeq.flatMap(vm => List("--vm", vm)) ++
          List(classOf[ClassForNameTest].getName)
        println(params)
        new CaliperRunner().run(params: _*)
    }
    System.exit(0)
  }

}