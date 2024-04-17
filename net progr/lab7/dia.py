def dijkstra(graph, start):
    num_nodes = len(graph)
    distances = [float('inf')] * num_nodes
    distances[start] = 0
    priority_queue = [(0, start)]
    
    while priority_queue:
        priority_queue.sort() 
        current_distance, current_node = priority_queue.pop(0)
        if current_distance > distances[current_node]:
            continue
        for neighbor, weight in graph[current_node]:
            distance = current_distance + weight
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                priority_queue.append((distance, neighbor))
        priority_queue = [(d, n) for d, n in priority_queue if n != current_node]
    return distances

graph = [
    [(1, 5), (2, 3)],           # Node 0: (neighbor, weight)
    [(0, 5), (2, 2), (3, 1)],   # Node 1
    [(0, 3), (1, 2), (3, 4), (4, 2)],  # Node 2
    [(1, 1), (2, 4), (4, 3), (5, 6)],  # Node 3
    [(2, 2), (3, 3), (5, 1)],   # Node 4
    [(3, 6), (4, 1)]            # Node 5
]

start_node = 0
shortest_distances = dijkstra(graph, start_node)
print("Shortest distances from node", start_node, "to all other nodes:")
print(shortest_distances)
