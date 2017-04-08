#include <array>
#include <iostream>
using namespace std;
const auto n = 15;

void swap(int &n, int &m) {
  int temp = n;
  n = m;
  m = temp;
}

void buble(array<int, n> &arr) {
  bool sort = false;
  int i = 0, j = 1;
  if (n < 2)
    return;

  for (;; i++, j++) {
    if (j == n) {
      if (sort) {
        i = 0;
        j = 1;
        sort = false;
      } else
        return;
    }

    if (arr[i] > arr[j]) {
      swap(arr[i], arr[j]);
      sort = true;
    }
  }
}

int main() {
  array<int, n> arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

  // sort
  buble(arr);

  // print
  for (const auto &num : arr)
    cout << num << ' ';
  cout << '\n';
}
