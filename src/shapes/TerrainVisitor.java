package shapes;

public interface TerrainVisitor
  {
  public void visit(Line line);

  public void visit(Rectangle rectangle);
  
  public void visit(Triangle triangle);
  
  public void visit(Ground ground);
  
  public void visit(Wall wall);
  
  public void visit(Entrance entrance);
  
  public void visit(Exit exit);
  

 
  }
