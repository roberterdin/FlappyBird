package eu.erdin.scala.flappy.utils

import eu.erdin.flappy.model.{Pipe, State}
import eu.erdin.java.flappy.FlappyState
import Physics.PIPE_WIDTH

/**
  * @author robert.erdin@gmail.com 
  *         created on 15/12/18.
  */
object StateTools {
  def flappyStateToState(fs: FlappyState, yVelocity: Double, coolDown: Int): State = {
    State(
      fs.getX,
      fs.getY,
      yVelocity,
      coolDown,
      fs.getPipes
        .filter(p => p(0) + PIPE_WIDTH >= fs.getX)
        .sortWith((p1, p2) => p1(0) < p2(0) )
        .map(p => Pipe(p(0), p(1))).toList
    )
  }
}
