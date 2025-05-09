import random

def random_access(arr: list[int]) -> int:
    random_index = random.randint(0, len(arr) - 1)
    random_num = arr[random_index]
    return random_num


def insert(arr: list[int], index: int, value: int) -> None:
    for i in range(len(arr) - 1, index, -1):
        arr[i] = arr[i - 1]
    arr[index] = value


def remove(arr: list[int], index: int) -> None:
    for i in range(index, len(arr) - 1):
        arr[i] = arr[i + 1]


def find(arr: list[int], target: int) -> int:
    for i in range(len(arr)):
        if arr[i] == target:
            return i
    return -1

def extend(arr: list[int], enlarge: int) -> list[int]:
    res: list[int] = [0] * (len(arr) + enlarge)
    for i in range(len(arr)):
        res[i] = arr[i]
    return res

# test case
if __name__ == "__main__":
    arr = [1, 2, 3, 4, 5]
    # random access
    random_num = random_access(arr)
    print(random_num)

    # insert
    insert(arr, 1, 3)
    print(arr)

    # remove
    remove(arr, 3)
    print(arr)

    # find
    print(find(arr, 3))    

    # extend
    print(extend(arr, 2))