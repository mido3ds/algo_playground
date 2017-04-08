import quick_find, quick_union, qupc

if __name__=='__main__':
    for module in [qupc, quick_union, quick_find]:
        print('using UF from module: ', module.__name__)
        u = module.UF(10)
        for i in range(int(input('iterations: '))):
            a,b = map(int, input('> ').split())
            print('before', u.is_connected(a, b))
            u.union(a, b)
            print('after',u.is_connected(a, b), '\n')