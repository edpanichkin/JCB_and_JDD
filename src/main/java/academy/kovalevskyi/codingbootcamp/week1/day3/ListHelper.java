package academy.kovalevskyi.codingbootcamp.week1.day3;

import java.util.function.Function;

public class ListHelper{

  public static <T> int length(ListNode<T> someNode) {
    if (someNode.prev == null && someNode.next == null) {
      return 1;
    } else if(someNode != null) {
 
      int count = 1;
      ListNode<T> iterator = someNode;
      while(iterator.getNext() != null) {
        count++;
        iterator = iterator.getNext();
      }
      iterator = someNode;
      while(iterator.getPrev() != null) {
        count++;
        iterator = iterator.getPrev();
      }
      return count;
     }
   return 0;
  }

    public static <T> ListNode<T> addToEnd(ListNode<T> someNode, T newValue) {
        return null;
    }
    public static <T> ListNode<T> addToStart(ListNode<T> someNode, T newValue) {
        return null;
    }
  public static <T> boolean contains(ListNode<T> someNode, T value) {
    if (someNode.prev == null && someNode.next == null) {
      if (someNode.getValue() == value) {
        return true;
      } else {
        return false;
      }
    }
    ListNode<T> iterator = someNode;
    if (someNode.getPrev() == null) {
      while(iterator.getNext() != null) {
        if (iterator.getValue() == value) {
          return true;
        }
        iterator = iterator.getNext();
      } 
    }    
    if (someNode.getNext() == null) {
      iterator = someNode;
      while(iterator.getPrev() != null) {
        if (iterator.getValue() == value) {
          return true;
        }
        iterator = iterator.getPrev();
      } 
    }    
    return false;
  }

    public static <T, R> ListNode<R> map(ListNode<T> someNode, Function<T, R> mapFunction) {
        return null;
    }
    public static <T> ListNode<T> insertAfter(ListNode<T> prev, T newValue) {
        ListNode<T> newNode = new ListNode<>(prev, prev.getNext(), newValue);
        prev.setNext(newNode);
        return newNode;
    }
    public static <T> void insertAfter(ListNode<T> prev, T[] newValues) {
    }

    public static <T> T delete(ListNode<T> current) {
        if (current.getNext() == null) {
            current.getPrev().setNext(null);
            detachNode(current);
          return current.getValue();
        }
        if (current.getPrev() == null) {
            current.getNext().setPrev(null);
            detachNode(current);
          return current.getValue();
        }
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        detachNode(current);
        return current.getValue();
    }

    public static <T> void detachNode(ListNode<T> someNode) {
        someNode.setNext(null);
        someNode.setPrev(null);
    }
}

