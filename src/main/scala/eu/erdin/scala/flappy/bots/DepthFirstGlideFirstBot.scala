package eu.erdin.flappy.bots

import eu.erdin.flappy.model.{Pipe, State}
import eu.erdin.java.flappy.FlappyState
import eu.erdin.java.flappy.bot.FlappyBot

/**
  * Performs simple depth first search. The option not to flap ("glide") is expanded first while searching
  * @author robert.erdin@gmail.com
  */

class DepthFirstGlideFirstBot extends FlappyBot {
  val GRAVITY = 0.5D
  val BIRD_HEIGHT = 32
  val BIRD_WIDTH = 45
  val PIPE_WIDTH = 66
  val GAP_HEIGHT = 175

  private var iteration = 0
  private var coolDown = 0
  private var yVelocity = 0.5D


  private def flappyStateToState(fs: FlappyState): State = {
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

  override def flap(fs: FlappyState): Boolean = {
    iteration += 1
    val state: State = flappyStateToState(fs)

    if (state.coolDown > 0) {
      coolDown -= 1
      yVelocity += GRAVITY
      return false
    }

    if (explore(List(nextState(state, flap = false)))){
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
      return explore(nextState(state.head, flap = false) :: state)
    }
    if (explore(nextState(state.head, flap = false) :: state)){
      return true
    }
    if (explore(nextState(state.head, flap = true) :: state)){
      return true
    }
    false // by default
  }

  def nextState(state: State, flap: Boolean): State = {
    var yvel: Double = state.yVel + 0.5
    var coolDown = math.max(0, state.coolDown - 1)
    if (flap){
      coolDown = 10
      yvel = -10
    }
    State(
      state.x + 3,
      state.y + yvel.toInt,
      yvel,
      coolDown,
      state.pipes)
  }

  def isCrash(state: State): Boolean = {
    if(state.y < 0 - BIRD_HEIGHT){ // ceiling (only used if the heuristic is flap first)
      return true
    }

    if(state.y + BIRD_HEIGHT > 420){ // ground
      return true
    }

    !state.pipes.forall(p => {
      if (state.x + BIRD_WIDTH <= p.x || state.x >= p.x + PIPE_WIDTH){
        // is either before or after the pipe --> safe
        return false
      }
      if (state.y >= p.y && state.y + BIRD_HEIGHT <= p.y + GAP_HEIGHT){
        return false
      }
      return true
    })
  }
}
