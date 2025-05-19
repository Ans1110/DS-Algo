import re


class ListNode:
    def __init__(self, val: int):
        self.val = val
        self.next: ListNode | None = None

n0 = ListNode(1)
n1 = ListNode(2)
n2 = ListNode(3)
n3 = ListNode(4)
n4 = ListNode(5)

n0.next = n1
n1.next = n2
n2.next = n3
n3.next = n4

def insert(n0: ListNode, P: ListNode):
    n1 = n0.next
    P.next = n1
    n0.next = P

def remove(n0: ListNode):
    if not n0.next: return
    P = n0.next
    n1 = P.next
    n0.next = n1

def acccess(head: ListNode | None, index: int) -> ListNode | None:
    for i in range(index):
        if not head: return None
        head = head.next
    return head

def find(head: ListNode | None, target: int) -> int:
    index = 0
    while head:
        if head.val == target:
            return index
        head = head.next
        index += 1
    return -1
