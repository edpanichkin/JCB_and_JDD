package academy.kovalevskyi.codingbootcamp.week1.day1;

import academy.kovalevskyi.codingbootcamp.week1.day0.Point;

public class PointWithLabel extends PointWithValue<String> {

  public PointWithLabel(int coordinateX, int coordinateY, final String value) {
    super(coordinateX, coordinateY, value);
  }

  public String getLabel() {
    return (String) super.getValue();
  }

  @Override
  public int compareTo(Point o) {
    if (o.getClass() != PointWithLabel.class) {
      return super.compareTo(o);
    }
    return this.getLabel().compareTo(((PointWithLabel) o).getLabel());
  }
} 
