package org.example

import com.google.caliper.{Runner => CaliperRunner}
import com.olegych.EATest

object Runner {

  // main method for IDEs, from the CLI you can also run the caliper Runner directly
  // or simply use SBTs "run" action
  def main(args: Array[String]) {
    // we simply pass in the CLI args,
    // we could of course also just pass hardcoded arguments to the caliper Runner
    CaliperRunner.main(classOf[EATest], args ++ Array(/*"--measureMemory",*/"-JEA=-XX:+DoEscapeAnalysis,-XX:-DoEscapeAnalysis", "-Jserver=-server"))
  }

}