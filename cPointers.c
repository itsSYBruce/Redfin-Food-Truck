#include <stdio.h>
#include <stdlib.h>
void swap(int *a, int *b);

int main(){
  int i = 5;
  int j = 10;
  swap(&i, &j);
  printf("%d, %d\n", i, j);

  //https://www.geeksforgeeks.org/pointer-array-array-pointer/
  int a[2][5] = {{1,2,3,4,5}, {6,7,8,9,10}};
  // p is a pointer that points to an array of 5 ints
  int (*p)[5] = a;
  printf("%p, %p\n", *p, *a+1);

  int a2 [] = {9,8,7,6};
  //or int *ptr = a2;
  int *ptr = &a2[0];
  printf("%d\n", *ptr);

  int r = 5;
  int c = 5;
  int *str = (int *)malloc(r * c * sizeof(int));
  free(str);

  char * ch = "aaaa";
  //or
  int *str1 [r];
  for (int k = 0; k < r; k++)
    str1[k] = (int *)malloc(c * sizeof(int));
  free(str1);
  //or
  int ** str2 = (int **)malloc(c * sizeof(int*));
  for (int m = 0; m < r; m++)
      str2[m] = (int *)malloc(c * sizeof(int));
  free(str2);

  return 0;
}

void swap(int *a, int *b) {
  int temp = *a;
  *a = *b;
  *b = temp;
}
