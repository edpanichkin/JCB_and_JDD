package academy.kovalevskyi.codingbootcamp.week1.day3;

public class ListNode<T> {
    public ListNode<T> prev;
    public ListNode<T> next;
    T value;

    public ListNode(ListNode<T> prev, ListNode<T> next, T value) {
        this.prev = prev;
        this.next = next;
        this.value = value;
    }

    public ListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

  @Override
  public String toString() {
    if(prev == null && next == null) {
      return  "Node{" +
                "prevValue=null" +
                ", nextValue=null" +
                ", thisValue=" + value +
                "}";
    }       
    if(prev == null ) {
      return  "Node{" +
                "prevValue=null" +
                ", nextValue=" + next.getValue() +
                ", thisValue=" + value +
                "}";
    }       
    if(next == null ) {
      return  "Node{" +
                "prevValue=" + prev.getValue() +
                ", nextValue=null" +
                ", thisValue=" + value +
                "}";
    }       
    return  "Node{" +
                "prevValue=" + prev.getValue() +
                ", nextValue=" + next.getValue() +
                ", thisValue=" + value +
                "}";
  }
}
