type StackNode<T> = {
  value: T;
  next: StackNode<T> | null;
};

class LinkedListStack<T> {
  private stackPeek: StackNode<T> | null;
  private stkSize: number;

  constructor() {
    this.stackPeek = null;
    this.stkSize = 0;
  }

  get size(): number {
    return this.stkSize;
  }

  isEmpty(): boolean {
    return this.size === 0;
  }

  push(value: T): void {
    const node: StackNode<T> = { value, next: null };
    if (this.stackPeek) {
      node.next = this.stackPeek;
    }
    this.stackPeek = node;
    this.stkSize++;
  }

  pop(): T {
    const node = this.stackPeek;
    if (node) {
      this.stackPeek = this.stackPeek!.next;
      this.stkSize--;
      return node.value;
    }
    throw new Error("Stack is empty");
  }

  peek(): T {
    if (this.stackPeek) {
      return this.stackPeek.value;
    }
    throw new Error("Stack is empty");
  }

  toArray(): T[] {
    const arr: T[] = new Array<T>(this.stkSize);
    let currentNode = this.stackPeek;
    for (let i = this.stkSize - 1; i >= 0; i--) {
      arr[i] = currentNode!.value;
      currentNode = currentNode!.next;
    }
    return arr;
  }
}

class ArrayStack<T> {
  constructor(private stack: T[] = []) {}

  get size(): number {
    return this.stack.length;
  }

  isEmpty(): boolean {
    return this.stack.length === 0;
  }

  push(value: T): void {
    this.stack.push(value);
  }

  pop(): T {
    if (this.isEmpty()) throw new Error("Stack is empty");
    return this.stack.pop()!;
  }

  peek(): T {
    if (this.isEmpty()) throw new Error("Stack is empty");
    return this.stack[this.stack.length - 1];
  }

  toArray(): T[] {
    return [...this.stack];
  }
}
