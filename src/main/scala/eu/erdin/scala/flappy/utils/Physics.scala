package eu.erdin.scala.flappy.utils

import eu.erdin.flappy.model.State

/**
  * @author robert.erdin@gmail.com 
  *         created on 15/12/18.
  */
object Physics {
  val GRAVITY = 0.5D
  val BIRD_HEIGHT = 32
  val BIRD_WIDTH = 45
  val PIPE_WIDTH = 66
  val GAP_HEIGHT = 175

  def isCrash(state: State): Boolean = {
    if (state.y < 0 - BIRD_HEIGHT) { // ceiling (only used if the heuristic is flap first)
      return true
    }

    if (state.y + BIRD_HEIGHT > 420) { // ground
      return true
    }

    !state.pipes.forall(p => {
      if (state.x + BIRD_WIDTH <= p.x || state.x >= p.x + PIPE_WIDTH) {
        // is either before or after the pipe --> safe
        return false
      }
      if (state.y >= p.y && state.y + BIRD_HEIGHT <= p.y + GAP_HEIGHT) {
        return false
      }
      return true
    })
  }
}
