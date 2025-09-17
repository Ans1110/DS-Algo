package seqlist

import "errors"

type List struct {
	capacity    int
	data        []int
	arrSize     int
	extendRatio int
}

func NewList(cap int) *List {
	return &List{
		capacity:    cap,
		data:        make([]int, cap),
		arrSize:     0,
		extendRatio: 2,
	}
}

func (l *List) Size() int {
	return l.arrSize
}

func (l *List) Cap() int {
	return l.capacity
}

func (l *List) ExtendCap() {
	l.data = append(l.data, make([]int, l.capacity*(l.extendRatio-1))...)
}

func (l *List) ToArray() []int {
	return l.data[:l.arrSize]
}

func (l *List) Get(index int) (int, error) {
	if index < 0 || index >= l.arrSize {
		return 0, errors.New("index out of range")
	}
	return l.data[index], nil
}

func (l *List) Set(val int, index int) error {
	if index < 0 || index >= l.arrSize {
		return errors.New("index out of range")
	}
	l.data[index] = val
	return nil
}

func (l *List) Append(val int) {
	if l.arrSize == l.capacity {
		l.ExtendCap()
	}
	l.data[l.arrSize] = val
	l.arrSize++
}

func (l *List) Insert(val int, index int) {
	if index < 0 || index >= l.arrSize {
		errors.New("index out of range")
	}

	if l.arrSize == l.capacity {
		l.ExtendCap()
	}

	for i := l.arrSize - 1; i >= index; i-- {
		l.data[i+1] = l.data[i]
	}
	l.data[index] = val
	l.arrSize++
}

func (l *List) Remove(index int) int {
	if index < 0 || index >= l.arrSize {
		errors.New("index out of range")
	}
	val := l.data[index]
	for i := index; i < l.arrSize-1; i++ {
		l.data[i] = l.data[i+1]
	}
	l.arrSize--
	return val
}
