def bellman_ford(graph, start):
    num_nodes = len(graph)
    distances = [float('inf')] * num_nodes
    distances[start] = 0
    
    # Relax edges repeatedly
    for _ in range(num_nodes - 1):
        for u in range(num_nodes):
            for v, weight in graph[u]:
                if distances[u] + weight < distances[v]:
                    distances[v] = distances[u] + weight
    
    # Check for negative cycles
    for u in range(num_nodes):
        for v, weight in graph[u]:
            if distances[u] + weight < distances[v]:
                print("Graph contains negative cycle")
                return
    
    return distances

# Example usage:
graph = [
    [(1, 6), (3, 7)],     # Node 0: (neighbor, weight)
    [(2, 5), (3, 8), (4, -4)], # Node 1
    [(1, -2)],            # Node 2
    [(2, -3), (4, 9)],    # Node 3
    [(0, 2)]              # Node 4
]

start_node = 0
shortest_distances = bellman_ford(graph, start_node)
print("Shortest distances from node", start_node, "to all other nodes:")
print(shortest_distances)
