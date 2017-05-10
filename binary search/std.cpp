#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main(int argc, char* argv[])
{
    // vector<int> s = { 10, 9, 8, 7, 6, 5, 4, 1};
    vector<int> s = { 0, 0, 0, 0, 1, 1, 1};
    sort(s.begin(), s.end());
    cout << binary_search(s.begin(), s.end(), 9) << endl;

    auto some = lower_bound(s.begin(), s.end(), 1); // gets first 1 -> 1 1 1 
    while (some != s.end())
        cout << *some << ' ', some++;
    cout << endl;

    auto another = upper_bound(s.begin(), s.end(), 0); // gets what after last 0 -> 1 1 1 
    while(another != s.end())
        cout << *another << ' ', another++;
    cout << endl;

}