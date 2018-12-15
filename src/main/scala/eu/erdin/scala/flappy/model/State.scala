package eu.erdin.flappy.model

/**
  * @author robert.erdin@gmail.com 
  *         created on 05/08/17.
  */
case class State(x: Int, y: Int, yVel: Double, coolDown: Int, pipes: List[Pipe]){
  def nextState(flap: Boolean): State = {
    var nextYvel: Double = yVel + 0.5
    var nextCoolDown: Int = math.max(0, coolDown - 1)
    if (flap){
      nextCoolDown = 10
      nextYvel = -10
    }
    State(
      x + 3,
      y + nextYvel.toInt,
      nextYvel,
      nextCoolDown,
      pipes)
  }
}