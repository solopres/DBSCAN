```
expandCluster(Point root, List<Point> neighbors, Cluster cluster, List<Point> data)

add root to cluster as a core point
set root status to clustered

initialize queue with neighbors in it

for index in queue
	get current point from top of queue

	if the point is noise set it as a border point in the cluster

	skip any visited or clustered point

	set current point status to visited
	get list of current point's neighbors

	if current point has the minimum neighbors it is a core point
		add it to cluster as a core point
		add current point's neighbors to the queue
	else it is a border
		add it to the cluster as a border point

	set current point status to clustered
```
