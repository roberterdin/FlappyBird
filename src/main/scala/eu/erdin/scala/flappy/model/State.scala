package eu.erdin.flappy.model

/**
  * @author robert.erdin@gmail.com 
  *         created on 05/08/17.
  */
case class State(x: Int, y: Int, yVel: Double, coolDown: Int, pipes: List[Pipe])
