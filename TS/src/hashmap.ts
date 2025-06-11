class Pair {
  constructor(public key: number, public value: string) {}
}

class ArrayHashMap {
  private readonly buckets: (Pair | null)[];

  constructor() {
    this.buckets = new Array(100).fill(null);
  }

  private hashFn(key: number): number {
    return key % this.buckets.length;
  }

  public get(key: number): string | null {
    let index = this.hashFn(key);
    let pair = this.buckets[index];
    if (pair === null) return null;
    return pair.value;
  }

  public set(key: number, value: string): void {
    let index = this.hashFn(key);
    this.buckets[index] = new Pair(key, value);
  }

  public delete(key: number): void {
    let index = this.hashFn(key);
    this.buckets[index] = null;
  }

  public entries(): (Pair | null)[] {
    let arr: (Pair | null)[] = [];
    for (let i = 0; i < this.buckets.length; i++) {
      if (this.buckets[i]) arr.push(this.buckets[i]);
    }
    return arr;
  }

  public keys(): (number | undefined)[] {
    let arr: (number | undefined)[] = [];
    for (let i = 0; i < this.buckets.length; i++) {
      if (this.buckets[i]) arr.push(this.buckets[i]?.key);
    }
    return arr;
  }

  public values(): (string | undefined)[] {
    let arr: (string | undefined)[] = [];
    for (let i = 0; i < this.buckets.length; i++) {
      if (this.buckets[i]) arr.push(this.buckets[i]?.value);
    }
    return arr;
  }

  public print(): void {
    let pairSet = this.entries();
    for (const pair of pairSet) {
      console.log(`${pair?.key} -> ${pair?.value}`);
    }
  }
}

class HashMapChaining {
  #size: number;
  #capacity: number;
  #loadThreshold: number;
  #extendRatio: number;
  #buckets: Pair[][];
  constructor() {
    this.#size = 0;
    this.#capacity = 16;
    this.#loadThreshold = 0.75;
    this.#extendRatio = 2;
    this.#buckets = new Array(this.#capacity).fill(null).map((x) => []);
  }

  #hashFn(key: number): number {
    return key % this.#capacity;
  }

  #loadFactor(): number {
    return this.#size / this.#capacity;
  }

  #extend(): void {
    const bucketsTmp = this.#buckets;
    this.#capacity *= this.#extendRatio;
    this.#buckets = new Array(this.#capacity).fill(null).map((x) => []);
    this.#size = 0;

    for (const bucket of bucketsTmp) {
      for (const pair of bucket) {
        this.put(pair.key, pair.value);
      }
    }
  }

  public get(key: number): string | null {
    const index = this.#hashFn(key);
    const buckets = this.#buckets[index];
    if (!buckets) return null;
    for (const pair of buckets) {
      if (pair.key === key) return pair.value;
    }
    return null;
  }

  public put(key: number, value: string): void {
    if (this.#loadFactor() > this.#loadThreshold) this.#extend();

    const index = this.#hashFn(key);
    const buckets = this.#buckets[index];

    for (const pair of buckets) {
      if (pair.key === key) {
        pair.value = value;
        return;
      }
    }

    const pair = new Pair(key, value);
    buckets.push(pair);
    this.#size++;
  }

  public delete(key: number): void {
    const index = this.#hashFn(key);
    let buckets = this.#buckets[index];
    if (!buckets) return;
    for (let i = 0; i < buckets.length; i++) {
      if (buckets[i].key === key) {
        buckets.splice(i, 1);
        this.#size--;
        return;
      }
    }
  }

  public entries(): (Pair | null)[] {
    let arr: (Pair | null)[] = [];
    for (const bucket of this.#buckets) {
      if (bucket) arr.push(...bucket);
    }
    return arr;
  }

  public keys(): number[] {
    let arr: number[] = [];
    for (const bucket of this.#buckets) {
      if (bucket) arr.push(...bucket.map((x) => x.key));
    }
    return arr;
  }

  public values(): string[] {
    let arr: string[] = [];
    for (const bucket of this.#buckets) {
      if (bucket) arr.push(...bucket.map((x) => x.value));
    }
    return arr;
  }

  public print(): void {
    for (const bucket of this.#buckets) {
      if (!bucket) continue;
      let res: string[] = [];
      for (const pair of bucket) {
        res.push(`${pair.key} -> ${pair.value}`);
      }
      console.log(res.join(", "));
    }
  }
}

class HashMapOpenAddressing {
  constructor(
    private size: number = 0,
    private capacity: number = 16,
    private loadThreshold: number = 0.75,
    private extendRatio: number = 2,
    private buckets: (Pair | null)[] = new Array(16).fill(null),
    private TOMBSTONE: Pair = new Pair(-1, "-1")
  ) {}

  private hashFn(key: number): number {
    return key % this.capacity;
  }

  private loadFactor(): number {
    return this.size / this.capacity;
  }

  private extend(): void {
    const bucketsTmp = this.buckets;
    this.capacity *= this.extendRatio;
    this.buckets = new Array(this.capacity).fill(null);
    this.size = 0;

    for (const pair of bucketsTmp) {
      if (pair !== null && pair !== this.TOMBSTONE) {
        this.put(pair.key, pair.value);
      }
    }
  }

  public findBucket(key: number): number {
    let index = this.hashFn(key);
    let firstTombstone = -1;

    while (this.buckets[index]!.key === key) {
      if (this.buckets[index]!.key === key) {
        if (firstTombstone !== -1) {
          this.buckets[firstTombstone] = this.buckets[index];
          this.buckets[index] = this.TOMBSTONE;
          return firstTombstone;
        }
        return index;
      }
      if (firstTombstone === -1 && this.buckets[index] === this.TOMBSTONE) {
        firstTombstone = index;
      }
      index = (index + 1) % this.capacity;
    }

    return firstTombstone === -1 ? index : firstTombstone;
  }

  public get(key: number): string | null {
    const index = this.findBucket(key);

    if (
      this.buckets[index] !== null &&
      this.buckets[index] !== this.TOMBSTONE
    ) {
      return this.buckets[index]!.value;
    }
    return null;
  }

  public put(key: number, value: string): void {
    if (this.loadFactor() > this.loadThreshold) this.extend();

    const index = this.findBucket(key);

    if (
      this.buckets[index] !== null &&
      this.buckets[index] !== this.TOMBSTONE
    ) {
      this.buckets[index]!.value = value;
      return;
    }
    this.buckets[index] = new Pair(key, value);
    this.size++;
  }

  public delete(key: number): void {
    const index = this.findBucket(key);
    if (
      this.buckets[index] !== null &&
      this.buckets[index] !== this.TOMBSTONE
    ) {
      this.buckets[index] = this.TOMBSTONE;
      this.size--;
    }
  }

  public entries(): (Pair | null)[] {
    let arr: (Pair | null)[] = [];
    for (const pair of this.buckets) {
      if (pair === null || pair === this.TOMBSTONE) continue;
      arr.push(pair);
    }
    return arr;
  }

  public keys(): number[] {
    let arr: number[] = [];
    for (const pair of this.entries()) {
      arr.push(pair!.key);
    }
    return arr;
  }

  public values(): string[] {
    let arr: string[] = [];
    for (const pair of this.entries()) {
      arr.push(pair!.value);
    }
    return arr;
  }

  public print(): void {
    for (const pair of this.buckets) {
      if (pair === null) {
        console.log("null");
      } else if (pair === this.TOMBSTONE) {
        console.log("tombstone");
      } else {
        console.log(`${pair!.key} -> ${pair!.value}`);
      }
    }
  }
}
