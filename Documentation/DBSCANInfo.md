# Description of DBSCAN
DBSCAN is a machine learning algorithm that groups datapoints that are in relative, close proximity to each other.  
Clusters are based on the density of datapoints rather than being predefined. Datapoints with n neighboring datapoints are classified as core points.
A cluster can have more than one core point.  
Datapoints that are within the "neighborhood" of core components and are not core points themselves are called border points.  
A neighborhood is determined by a distance metric (i.e. Euclidian distance) through the K-Nearest Neighbors (KNN) algorithm.  
Datapoints that are not within the neighborhood of at least one core component are outliers.

The image below showcases the differences between DBSCAN and its KNN counterpart, K-Means Clustering.

<img width="1280" height="640" alt="image" src="https://github.com/user-attachments/assets/2b0d6d17-7217-47ec-9924-fba3003c3c84" />

Image obtained from [Medium](https://medium.com/@abhaysingh71711/dbscan-explained-unleashing-the-power-of-density-based-clustering-72a51ba40fdf).
