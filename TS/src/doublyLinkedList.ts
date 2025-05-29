type DoublyListNode<T> = {
  val: T;
  prev: DoublyListNode<T> | null;
  next: DoublyListNode<T> | null;
};

class DoublyLinkedList<T> {
  constructor(
    public head: DoublyListNode<T> | null,
    public tail: DoublyListNode<T> | null
  ) {}

  prepend(value: T): void {
    const newNode: DoublyListNode<T> = { val: value, prev: null, next: null };

    if (this.head) {
      this.head.prev = newNode;
    }
    this.head = newNode;

    if (!this.tail) {
      this.tail = newNode;
    }

    return;
  }

  append(value: T): void {
    const newNode: DoublyListNode<T> = { val: value, prev: null, next: null };

    if (!this.head) {
      this.head = newNode;
      this.tail = newNode;

      return;
    }

    if (this.tail) {
      this.tail.next = newNode;
      newNode.prev = this.tail;
      this.tail = newNode;
    }

    return;
  }

  insert(value: T, rawIndex: number): void {
    const index = rawIndex > 0 ? rawIndex : 0;

    if (index === 0) {
      this.prepend(value);
      return;
    }

    let currentNode = this.head;
    let count = 1;
    while (currentNode) {
      if (count === index) break;
      currentNode = currentNode.next;
      count++;
    }

    if (currentNode) {
      const newNode: DoublyListNode<T> = { val: value, prev: null, next: null };
      newNode.next = currentNode.next;
      currentNode.next = newNode;
      if (newNode.next) {
        newNode.next.prev = newNode;
      } else {
        this.tail = newNode;
      }
    } else {
      this.append(value);
    }

    return;
  }

  delete(value: T): void {
    if (!this.head) return;

    let deleteNode = null;
    let currentNode = this.head;

    while (currentNode) {
      if (currentNode.val === value) {
        deleteNode = currentNode;

        if (deleteNode === this.head) {
          this.head = deleteNode.next;

          if (this.head) {
            this.head.prev = null;
          }

          if (deleteNode === this.tail) {
            this.tail = null;
          }
        } else if (deleteNode === this.tail) {
          this.tail = deleteNode.prev;
          if (this.tail) {
            this.tail.next = null;
          }
        } else {
          const prevNode = deleteNode.prev;
          const nextNode = deleteNode.next;
          if (prevNode) {
            prevNode.next = nextNode;
          }
          if (nextNode) {
            nextNode.prev = prevNode;
          }
        }
      }
      if (currentNode.next) {
        currentNode = currentNode.next;
      }
    }
    return;
  }

  deleteHead(): void {
    if (!this.head) return;

    if (this.head.next) {
      this.head = this.head.next;
      this.head.prev = null;
    } else {
      this.head = null;
      this.tail = null;
    }
    return;
  }

  deleteTail(): void {
    if (!this.tail) return;

    if (this.head === this.tail) {
      this.head = null;
      this.tail = null;
    }

    this.tail = this.tail!.prev;
    this.tail!.next = null;
    return;
  }

  deleteAt(index: number): void {
    if (!this.head) return;

    if (index === 0) {
      this.deleteHead();
      return;
    }

    let currentNode = this.head;
    let count = 1;
    while (currentNode?.next) {
      if (count === index) break;
      currentNode = currentNode.next;
      count++;
    }

    if (currentNode?.next) {
      const prevNode = currentNode.prev;
      const nextNode = currentNode.next;
      if (prevNode) {
        prevNode.next = nextNode;
      }
      if (nextNode) {
        nextNode.prev = prevNode;
      }
    }
    return;
  }

  find(target: T): DoublyListNode<T> | null {
    if (!this.head) return null;

    let currentNode = this.head;
    while (currentNode) {
      if (currentNode.val === target) return currentNode;
      if (!currentNode.next) break;
      currentNode = currentNode.next;
    }
    return null;
  }

  findIndex(target: T): number {
    if (!this.head) return -1;

    let currentNode = this.head;
    let index = 0;
    while (currentNode) {
      if (currentNode.val === target) return index;
      if (!currentNode.next) break;
      currentNode = currentNode.next;
      index++;
    }
    return -1;
  }

  reverse(): void {
    let currentNode = this.head;
    let prevNode = null;
    let nextNode = null;

    while (currentNode) {
      prevNode = currentNode.prev;
      nextNode = currentNode.next;
      currentNode.next = prevNode;
      currentNode.prev = nextNode;

      prevNode = currentNode;
      currentNode = nextNode;
    }
    this.head = prevNode;
    this.tail = this.head;
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
}
