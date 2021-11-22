package academy.kovalevskyi.codingbootcamp.week1.day3;

import java.util.function.Function;

public class ListHelper {

  public static <T> int length(ListNode<T> someNode) {
    if (someNode.prev == null && someNode.next == null) {
      return 1;
    } else if (someNode != null) {
      int count = 1;
      ListNode<T> iterator = someNode;
      while (iterator.getNext() != null) {
        count++;
        iterator = iterator.getNext();
      }
      iterator = someNode;
      while (iterator.getPrev() != null) {
        count++;
        iterator = iterator.getPrev();
      }
      return count;
    }
    return 0;
  }

  public static <T> ListNode<T> addToEnd(ListNode<T> someNode, T newValue) {
    ListNode<T> iterator = someNode;
    while (iterator.getNext() != null) {
      iterator = iterator.getNext();
    }
    ListNode<T> newNode = new ListNode<>(iterator, null, newValue);
    iterator.setNext(newNode); 
    return newNode;
  }

  public static <T> ListNode<T> addToStart(ListNode<T> someNode, T newValue) {
    ListNode<T> iterator = someNode;
    while (iterator.getPrev() != null) {
      iterator = iterator.getPrev();
    }
    ListNode<T> newNode = new ListNode<>(null, iterator, newValue);
    iterator.setPrev(newNode); 
    return newNode;
  }

  public static <T> boolean contains(ListNode<T> someNode, T value) {
    ListNode<T> iterator = someNode;
    if (iterator.getValue() == value) {
      return true;    
    }
    while (iterator.getNext() != null) {
      if (iterator.getNext().getValue() == value) {
        return true;
      }
      iterator = iterator.getNext();
    } 
    iterator = someNode;
    while (iterator.getPrev() != null) {
      if (iterator.getPrev().getValue() == value) {
        return true;
      }
      iterator = iterator.getPrev();
    } 
    return false;
  }

  public static <T, R> ListNode<R> map(ListNode<T> someNode, Function<T, R> mapFunction) {
    if (someNode.getPrev() == null && someNode.getNext() == null) {
      return new ListNode<R>(null, null, mapFunction.apply(someNode.getValue()));
    }
    ListNode<T> iterator = someNode;
    while (iterator.getPrev() != null) {
      iterator = iterator.getPrev();
    }
    ListNode<R> newNode = new ListNode<>(null, null, mapFunction.apply(iterator.getValue()));
    while (iterator.getNext() != null) {
      newNode = insertAfter(newNode, mapFunction.apply(iterator.getNext().getValue()));
      iterator = iterator.getNext();
    } 
    return newNode;    
  }

  public static <T> ListNode<T> insertAfter(ListNode<T> prev, T newValue) {
    ListNode<T> newNode = new ListNode<>(prev, prev.getNext(), newValue);
    prev.setNext(newNode);
    return newNode;
  }

  public static <T> void insertAfter(ListNode<T> prev, T[] newValues) {
    for (int i = 0; i < newValues.length; i++) {
      prev = insertAfter(prev, newValues[i]);
    }
  }

  public static <T> T delete(ListNode<T> current) {
    ListNode<T> next = current.getNext();
    ListNode<T> prev = current.getPrev();
    current.setNext(null);
    current.setPrev(null);
    if (next != null) {
      next.setPrev(prev);
    }
    if (prev != null) {
      prev.setNext(next);
    }
    return current.getValue();
  }
}

