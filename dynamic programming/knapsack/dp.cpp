#include <stdio.h>

#define max(a, b) a > b ? a:b

// get max-val can be put in a knapsack of capacity `W`
int knapSack(int W, int N, int ws[], int vs[]) 
{
    int a[N+1][W+1];

    for (int n = 0; n <= N; n++)
    {
        for (int w = 0; w <= W; w++)
        {
            if (n == 0 || w == 0)
                a[n][w] = 0;
            else if (ws[n - 1] > w)
                a[n][w] = a[n - 1][w];
            else
                a[n][w] = max(vs[n - 1] + a[n - 1][w - ws[n - 1]], a[n - 1][w]);
        }
    }

    return a[N][W];
}

int main()
{
    int val[] = {60, 100, 120};
    int wt[] = {10, 20, 30};
    int  W = 50;
    int n = sizeof(val) / sizeof(val[0]);
    printf("%d\n", knapSack(W, n, wt, val));
    return 0;
}