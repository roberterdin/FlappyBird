package eu.erdin.flappy.bots

import eu.erdin.flappy.model.{Pipe, State}
import eu.erdin.java.flappy.FlappyState
import eu.erdin.java.flappy.bot.FlappyBot
import eu.erdin.scala.flappy.utils.Physics.{GRAVITY, PIPE_WIDTH, isCrash}
import eu.erdin.scala.flappy.utils.StateTools

/**
  * Performs simple depth first search. The decision whether to expand the flap or glide option first
  * is based on a simple heuristic (bird is below or above the middle of the next pipe)
  * @author robert.erdin@gmail.com
  */

class DepthFirstHeurisicBot extends FlappyBot {
  private var iteration = 0
  private var coolDown = 0
  private var yVelocity = 0.5D

  def updateState(flap: Boolean): Unit = if (flap) {
    yVelocity = -10
    coolDown = 10
  } else {
    yVelocity += GRAVITY
    coolDown = math.max(coolDown - 1, 0)
  }

  override def flap(fs: FlappyState): Boolean = {
    iteration += 1
    val state: State = StateTools.flappyStateToState(fs, yVelocity, coolDown)

    if (state.coolDown > 0) {
      coolDown -= 1
      yVelocity += GRAVITY
      return false
    }

    val firstAction = List(true, false).minBy(a => heuristic(state.nextState(a)))
    if(explore(List(state.nextState(flap = firstAction)))){
      updateState(firstAction)
      return firstAction
    }
    !firstAction
  }

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
    val findsGoal = List(true, false).sortBy(a => heuristic(state.head.nextState(a)))
      .exists(action => { explore(state.head.nextState(action) :: state)})
    if (findsGoal){
      return true
    }
    false // by default
  }

  def heuristic(state: State): Int = {
    // default pipe in the middle, 249 - 87
    math.abs(state.y - state.pipes.find(p => p.x + PIPE_WIDTH > state.x).getOrElse(Pipe(499,162)).x + 87)
  }
}
