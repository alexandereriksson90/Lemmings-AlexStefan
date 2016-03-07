package shapes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class ObservableList<TYPE> extends Observable implements Iterable<TYPE>
  {
  protected List<TYPE> list = new LinkedList<TYPE>();

  public void add(TYPE o)
    {
    list.add(o);
    setChanged();
    notifyObservers();
    }

  public void remove(TYPE o)
    {
    if (list.contains(o))
      {
      list.remove(o);
      setChanged();
      notifyObservers();
      }
    }

  public boolean contains(TYPE o)
    {
    return list.contains(o);
    }

  public void add(ObservableList<TYPE> toAdd)
    {
    list.addAll(toAdd.list);
    setChanged();
    notifyObservers();
    }

  public void remove(ObservableList<TYPE> toRemove)
    {
    list.removeAll(toRemove.list);
    setChanged();
    notifyObservers();
    }

  public void clear()
    {
    list.clear();
    setChanged();
    notifyObservers();
    }

  public int size()
    {
    return list.size();
    }

  public TYPE get(int index)
    {
    return list.get(index);
    }

  @Override
  public Iterator<TYPE> iterator()
    {
    return list.iterator();
    }
  }
