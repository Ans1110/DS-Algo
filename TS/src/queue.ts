type QueueNode<T> = {
  val: T;
  next: QueueNode<T> | null;
};

class LinkedListQueue<T> {
  constructor(
    private front: QueueNode<T> | null = null,
    private rear: QueueNode<T> | null = null,
    private queueSize: number = 0
  ) {}

  get size(): number {
    return this.queueSize;
  }

  isEmpty(): boolean {
    return this.size === 0;
  }

  enqueue(value: T): void {
    const node: QueueNode<T> = { val: value, next: null };
    if (!this.front) {
      this.front = node;
      this.rear = node;
    } else {
      this.rear!.next = node;
      this.rear = node;
    }
    this.queueSize++;
  }

  dequeue(): T {
    if (!this.front) throw new Error("Queue is empty");
    const node = this.front;
    this.front = this.front.next;
    this.queueSize--;
    return node.val;
  }

  peek(): T {
    if (this.size === 0) throw new Error("Queue is empty");
    return this.front!.val;
  }

  toArray(): T[] {
    let currentNode = this.front;
    const arr: T[] = new SeqList<T>(this.queueSize);
    for (let i = 0; i < this.queueSize; i++) {
      arr[i] = currentNode!.val;
      currentNode = currentNode!.next;
    }
    return arr;
  }
}

class ArrayQueue<T> {
  constructor(
    private queue: T[] = [],
    private front: number = 0,
    private queueSize: number = 0
  ) {}

  get capacity(): number {
    return this.queue.length;
  }

  get size(): number {
    return this.queueSize;
  }

  isEmpty(): boolean {
    return this.size === 0;
  }

  enqueue(value: T): void {
    if (this.size === this.capacity) throw new Error("Queue is full");
    const rear = (this.front + this.queueSize) % this.capacity;
    this.queue[rear] = value;
    this.queueSize++;
  }

  dequeue(): T {
    const front = this.peek();
    this.front = (this.front + 1) % this.capacity;
    this.queueSize--;
    return front;
  }

  peek(): T {
    if (this.size === 0) throw new Error("Queue is empty");
    return this.queue[this.front];
  }

  toArray(): T[] {
    const arr = new SeqList<T>(this.queueSize);
    for (let i = 0, j = this.front; i < this.queueSize; i++, j++) {
      arr[i] = this.queue[j % this.capacity];
    }
    return arr;
  }
}
