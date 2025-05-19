type ListNode<T> = {
  val: T;
  next: ListNode<T> | null;
};

class LinkedList<T> {
  constructor(
    public head: ListNode<T> | null,
    public tail: ListNode<T> | null
  ) {}

  prepend(value: T): void {
    const newNode: ListNode<T> = { val: value, next: null };
    this.head = newNode;
    !this.tail && (this.tail = newNode);
  }

  append(value: T): void {
    const newNode: ListNode<T> = { val: value, next: null };

    if (!this.head) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      this.tail!.next = newNode;
      this.tail = newNode;
    }
  }

  insert(value: T, rawIndex: number) {
    const index = rawIndex < 0 ? 0 : rawIndex;
    if (index === 0) {
      this.prepend(value);
    } else {
      let count = 1;
      let currentNode = this.head;
      const newNode: ListNode<T> = { val: value, next: null };
      while (currentNode) {
        if (count === index) break;
        currentNode = currentNode.next;
        count++;
      }
      if (currentNode) {
        newNode.next = currentNode.next;
        currentNode.next = newNode;
      } else {
        if (this.tail) {
          this.tail.next = newNode;
          this.tail = newNode;
        } else {
          this.head = newNode;
          this.tail = newNode;
        }
      }
    }
  }

  delete(value: T): void {
    if (!this.head) return;

    while (this.head && this.head.val === value) {
      this.head = this.head.next;
    }

    let currentNode = this.head;

    while (currentNode && currentNode.next) {
      if (currentNode.next.val === value) {
        currentNode.next = currentNode.next.next;
      } else {
        currentNode = currentNode.next;
      }
    }

    this.tail = this.head;
    while (this.tail && this.tail.next) {
      this.tail = this.tail.next;
    }
  }

  find(target: T): ListNode<T> | null {
    if (!this.head) return null;

    let currentNode: ListNode<T> | null = this.head;
    while (currentNode) {
      if (currentNode.val === target) return currentNode;
      currentNode = currentNode.next;
    }
    return null;
  }

  findIndex(target: T): number {
    if (!this.head) return -1;

    let currentNode: ListNode<T> | null = this.head;
    let index = 0;
    while (currentNode) {
      if (currentNode.val === target) return index;
      currentNode = currentNode.next;
      index++;
    }
    return -1;
  }

  deleteTail(): void {
    if (this.head === this.tail) {
      this.head = null;
      this.tail = null;
    }

    let currentNode = this.head;
    while (currentNode?.next) {
      if (!currentNode.next.next) {
        currentNode.next = null;
      } else {
        currentNode = currentNode.next;
      }
    }
    this.tail = currentNode;
  }

  deleteHead(): void {
    if (!this.head) return;

    if (this.head.next) {
      this.head = this.head.next;
    } else {
      this.head = null;
      this.tail = null;
    }
  }

  deleteAt(index: number): void {
    if (!this.head) return;

    if (index === 0) {
      this.deleteHead();
    } else {
      let currentNode = this.head;
      let count = 1;
      while (currentNode?.next) {
        if (count === index) break;
        currentNode = currentNode.next;
        count++;
      }
      if (currentNode?.next) {
        currentNode.next = currentNode.next.next;
      }
    }
  }

  toArray(): T[] {
    const nodes: T[] = [];

    let currentNode = this.head;
    while (currentNode) {
      nodes.push(currentNode.val);
      currentNode = currentNode.next;
    }
    return nodes;
  }

  toString(callback?: (value: T) => string): string {
    return this.toArray()
      .map((value: T) => (callback ? callback(value) : String(value)))
      .toString();
  }

  reverse(): void {
    let currentNode = this.head;
    let prevNode = null;
    let nextNode = null;

    while (currentNode) {
      nextNode = currentNode.next;
      currentNode.next = prevNode;

      prevNode = currentNode;
      currentNode = nextNode;
    }

    this.tail = this.head;
    this.head = prevNode;
  }
}
