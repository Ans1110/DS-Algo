// const arr: number[] = [1, 2, 3, 4, 5];

// const randomNum = (arr: number[]): number => {
//   const randomIndex = Math.floor(Math.random() * arr.length);
//   return arr[randomIndex];
// };

// const insert = (arr: number[], index: number, value: number): void => {
//   for (let i = arr.length - 1; i > index; i--) {
//     arr[i] = arr[i - 1];
//   }
//   arr[index] = value;
// };

// const remove = (arr: number[], index: number): void => {
//   for (let i = index; i < arr.length - 1; i++) {
//     arr[i] = arr[i + 1];
//   }
// };

// const find = (arr: number[], target: number): number => {
//   for (let i = 0; i < arr.length; i++) {
//     if (arr[i] === target) {
//       return i;
//     }
//   }
//   return -1;
// };

// const extend = (arr: number[], enlarge: number): number[] => {
//   const res: number[] = new Array(arr.length + enlarge);
//   for (let i = 0; i < arr.length; i++) {
//     res[i] = arr[i];
//   }

//   return res;
// };

// //text case
// //random access
// console.log(randomNum(arr));
// //insert
// insert(arr, 1, 3);
// console.log(arr);

// //remove
// remove(arr, 3);
// console.log(arr);

// //find
// console.log(find(arr, 3));

// //extend
// console.log(extend(arr, 2));

// console.log(arr);
