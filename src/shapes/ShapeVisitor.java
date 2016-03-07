package shapes;

public interface ShapeVisitor
  {
  public void visit(Line line);

  public void visit(Rectangle rectangle);
  
  public void visit(Triangle triangle);

  public void normal();

  public void emphasize();
  }
