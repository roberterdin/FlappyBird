package eu.erdin.scala.flappy

import eu.erdin.flappy.bots.{DepthFirstGlideFirstBot, DepthFirstHeurisicBot}
import eu.erdin.java.flappy.App

/**
  * @author robert.erdin@gmail.com 
  *         created on 16/07/17.
  */
object Main {

  def main(args: Array[String]) {
    App.main(new DepthFirstHeurisicBot)
  }
}
