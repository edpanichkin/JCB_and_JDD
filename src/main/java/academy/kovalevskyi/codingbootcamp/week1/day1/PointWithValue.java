package academy.kovalevskyi.codingbootcamp.week1.day1;

import academy.kovalevskyi.codingbootcamp.week1.day0.Point;
import java.util.function.Function;

public class PointWithValue<T> extends Point {

  public T value;
  
  public PointWithValue(int coordinateX, int coordinateY, T value) {
    super(coordinateX, coordinateY);    
    this.value = value;
  }

  public T getValue() {
    return this.value;
  } 

  @Override
  public String toString() {
    return "PointWithValue{ X: " + this.getX() + ", Y: " + this.getY()
      + ", value: " + this.getValue() + "}";
  } 
}

