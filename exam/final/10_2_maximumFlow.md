Ford-Fulkerson Algorithm
* Given a graph G with capacity C labeled between each edge
1. Determine a path from source to sink s->t
2. For graph G, determine flow, f(e), required by each edge such that maximum flow is reached at edge directed at sink.
    - Conservation rule
3. Create a residual graph, G_f,
    - For each edge determine a residual capacity
        a. If f(e) < C(e), put a forward edge with forward residual capacity ce' = Ce - f(e) 
        b. If f(e) > 0,    put a reverse edge with the residual capacity re = f(e)
4. Find a s->t path from s->t in G_f
5. Determine minimum capacity, b, of the s->t path
6. Augment f using b
    - f'(e) = f(e) + b ( if e is a forward edge in path p )
    - f'(e) = f(e) - b ( if e is a backward edge in path p )
