import cv2
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans

# Load the original image
image_path = r"C:\SEM 6\Diacare\Dataset\A NEW DATASET SPLIT\test\both\301686.png"
image = cv2.imread(image_path)
image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)  # Convert to RGB for visualization

# Convert to LAB color space for better segmentation
lab_image = cv2.cvtColor(image, cv2.COLOR_RGB2LAB)

# Reshape for K-Means clustering
pixels = lab_image.reshape((-1, 3))

# Apply K-Means Clustering
k = 3  # Number of tissue types
kmeans = KMeans(n_clusters=k, random_state=42, n_init=10)
labels = kmeans.fit_predict(pixels)

# Reshape labels back to the original image shape
segmented_labels = labels.reshape(image.shape[:2])

# Define custom colors for each cluster (RGBA with transparency)
color_map = {
    0: (255, 182, 193, 100),   # Light Pink for Healthy Tissue
    1: (0, 0, 0, 100),         # Black for Dead Tissue
    2: (173, 255, 47, 100),    # Greenish Yellow for Infected Tissue
}

# Create an empty overlay image
overlay = np.zeros((image.shape[0], image.shape[1], 4), dtype=np.uint8)

# Assign colors to each cluster
for cluster, color in color_map.items():
    overlay[segmented_labels == cluster] = color

# Convert overlay to BGR
overlay_bgr = cv2.cvtColor(overlay[:, :, :3], cv2.COLOR_RGBA2BGR)

# Blend the original image with the overlay (alpha blending)
alpha = 0.3  # Adjust transparency (lower = more transparent)
blended = cv2.addWeighted(image, 1, overlay_bgr, alpha, 0)

# Show the final output
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.imshow(image)
plt.title("Original Image")
plt.axis("off")

plt.subplot(1, 2, 2)
plt.imshow(blended)
plt.title("Segmented Tissue Overlay")
plt.axis("off")

plt.show()

print("Cluster Centers (LAB values):")
print(kmeans.cluster_centers_)