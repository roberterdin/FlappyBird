package eu.erdin.flappy.bots

import eu.erdin.flappy.model.State
import eu.erdin.java.flappy.FlappyState
import eu.erdin.java.flappy.bot.FlappyBot
import eu.erdin.scala.flappy.utils.Physics.{GRAVITY, PIPE_WIDTH, isCrash}
import eu.erdin.scala.flappy.utils.StateTools

/**
  * Performs simple depth first search. The option not to flap ("glide") is expanded first while searching
  * @author robert.erdin@gmail.com
  */

class DepthFirstGlideFirstBot extends FlappyBot {
  private var iteration = 0
  private var coolDown = 0
  private var yVelocity = 0.5D




  override def flap(fs: FlappyState): Boolean = {
    iteration += 1
    val state: State = StateTools.flappyStateToState(fs, yVelocity, coolDown)

    if (state.coolDown > 0) {
      coolDown -= 1
      yVelocity += GRAVITY
      return false
    }

    if (explore(List(state.nextState(flap = false)))){
      yVelocity += GRAVITY
      coolDown = math.max(coolDown - 1, 0)
      return false
    }
    yVelocity = -10
    coolDown = 10
    true
  }

  /**
    * eu.erdin.java.scala.flappy.model.State is a list for debugging purposes. Can be changed to a single state object.
    * @param state
    * @return
    */
  def explore(state: List[State]): Boolean = {
    if (state.head.x > state.head.pipes.head.x + PIPE_WIDTH){
      return true
    }
    if (isCrash(state.head)){
      return false
    }
    if (state.head.coolDown > 0){
      return explore(state.head.nextState(flap = false) :: state)
    }
    if (explore(state.head.nextState(flap = false) :: state)){
      return true
    }
    if (explore(state.head.nextState(flap = true) :: state)){
      return true
    }
    false // by default
  }
}
