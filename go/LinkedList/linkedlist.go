package linkedlist

import "errors"

type ListNode struct {
	Val  int
	Next *ListNode
	Prev *ListNode
}

func NewListNode(val int) *ListNode {
	return &ListNode{
		Val:  val,
		Next: nil,
		Prev: nil,
	}
}

type DoubleLinkedList struct {
	Head, Tail *ListNode
	LenV       int
}

func NewDoubleLinkedList() *DoubleLinkedList {
	return &DoubleLinkedList{
		Head: nil,
		Tail: nil,
		LenV: 0,
	}
}

func (l *DoubleLinkedList) PushFront(val int) {
	newNode := NewListNode(val)
	if l.LenV == 0 {
		l.Head = newNode
		l.Tail = newNode
	} else {
		newNode.Next = l.Head
		l.Head.Prev = newNode
		l.Head = newNode
	}
	l.LenV++
}

func (l *DoubleLinkedList) PushBack(val int) {
	newNode := NewListNode(val)
	if l.LenV == 0 {
		l.Head = newNode
		l.Tail = newNode
	} else {
		newNode.Prev = l.Tail
		l.Tail.Next = newNode
		l.Tail = newNode
	}
	l.LenV++
}

func (l *DoubleLinkedList) Insert(index int, newNode *ListNode) {
	if index > l.LenV || index < 0 {
		panic("index out of range")
	}
	curNode := l.Head
	for i := 0; i < index; i++ {
		curNode = curNode.Next
	}
	nextNode := curNode.Next
	newNode.Next = nextNode
	nextNode.Prev = newNode
	curNode.Next = newNode
	newNode.Prev = curNode
}

func (l *DoubleLinkedList) PopBack() (int, error) {
	if l.Tail == nil {
		return 0, errors.New("list is empty")
	}
	val := l.Tail.Val
	if l.Head == l.Tail {
		l.Head, l.Tail = nil, nil
	} else {
		l.Tail = l.Tail.Prev
		l.Tail.Next = nil
	}
	l.LenV--
	return val, nil
}

func (l *DoubleLinkedList) Delete(node *ListNode) {
	val := node.Val
	curNode := l.Head
	for curNode != nil {
		if curNode.Val == val {
			deleteNode := curNode
			if deleteNode == l.Head {
				l.Head = l.Head.Next
				l.Head.Prev = nil
				if deleteNode == l.Tail {
					l.Tail = l.Tail.Prev
					l.Tail.Next = nil
				}
				l.LenV--

			} else if deleteNode == l.Tail {
				l.Tail = l.Tail.Prev
				l.Tail.Next = nil
				l.LenV--

			} else {
				preNode := curNode.Prev
				nextNode := curNode.Next
				if preNode != nil {
					preNode.Next = nextNode
				}
				if nextNode != nil {
					nextNode.Prev = preNode
				}
				l.LenV--
			}
		}
		curNode = curNode.Next
	}
}

func (l *DoubleLinkedList) DeleteFront() {
	if l.Head != nil {
		if l.Head == l.Tail {
			l.Head, l.Tail = nil, nil
		}
		l.Head = l.Head.Next
		l.Head.Prev = nil
		l.LenV--
	}
	panic("list is empty")
}

func (l *DoubleLinkedList) DeleteBack() {
	if l.Tail != nil {
		if l.Head == l.Tail {
			l.Head, l.Tail = nil, nil
		}
		l.Tail = l.Tail.Prev
		l.Tail.Next = nil
		l.LenV--
	}
	panic("list is empty")
}

func (l *DoubleLinkedList) FindNode(target int) (int, error) {
	curNode := l.Head
	var index int = 0
	for curNode != nil {
		if curNode.Val == target {
			return index, nil
		}
		curNode = curNode.Next
		index++
	}
	return 0, errors.New("not found")
}

func (l *DoubleLinkedList) Reverse() {
	curNode := l.Head
	var preNode *ListNode = nil
	for curNode != nil {
		next := curNode.Next
		curNode.Next = preNode
		curNode.Prev = next

		preNode = curNode
		curNode = next
	}
	l.Tail = l.Head
	l.Head = preNode
}
