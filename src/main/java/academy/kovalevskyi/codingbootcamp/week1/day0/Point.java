package academy.kovalevskyi.codingbootcamp.week1.day0;

public class Point implements Comparable<Point> {
  private final int coordinateX;
  private final int coordinateY;

  public Point(final int coordinateX, final int coordinateY) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
  }

  public int getX() {
    return this.coordinateX;  
  }

  public int getY() {
    return this.coordinateY;  
  }

  public Point sum(final Point that) {
    return new Point((that.getX() + this.getX()), (that.getY() + this.getY()));
  }

  public Point updateY(int newY) {
    return new Point(this.getX(), newY);
  }

  public Point updateX(int newX) {
    return new Point(newX, this.getY());
  }

  public int distanceTo(Point that) {
    return (int) NumberUtils.power(this.getX() - that.getX(), 2) 
      + (int) NumberUtils.power(this.getY() - that.getY(), 2);
  } 

  @Override
  public int compareTo(Point o) {
    int a = this.getX() + this.getY();
    int b = o.getX() + o.getY();
    if (a > b || a < b) {
      return a - b;
    }    
    return 0;
  } 
  
  @Override
  public int hashCode() {
    return this.getX() + this.getY();  
  }

  @Override
  public String toString() {
    return "Point{X: " + this.getX() + ", Y: " + this.getY() + "}";
  } 
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point point = (Point) o;
    return this.getX() == point.getX() && this.getY() == point.getY();
  }
}
