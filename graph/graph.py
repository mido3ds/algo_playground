import ds
description = {
        1: {5: 13, 2: 30},
        2: {22: 7, 1: 30, 5: 18},
        22: {16: 3, 2: 7},
        16: {22: 3},
        5: {2: 18, 1: 13, 3: 16},
        3: {5: 16, 36: 50},
        36: {3: 50},
    }

random_descr = {
        "begin": 1,
        "end": 10,
        "max edges": 5,
        "min edges": 2,
    }

def main():
    graph = ds.Graph(description)
    
    print(graph.mst())
    

if __name__=="__main__":
    main()