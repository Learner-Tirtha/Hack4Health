from fastapi import FastAPI, UploadFile, File
from fastapi.responses import JSONResponse, Response
from fastapi.middleware.cors import CORSMiddleware
import cv2
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
import io
from PIL import Image

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Change this to specific domains in production
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
def segment_image(image_bytes):
    # Load image from bytes
    image = Image.open(io.BytesIO(image_bytes)).convert("RGB")
    image = np.array(image)

    # Convert to LAB color space
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
        0: (255, 182, 193, 100),  # Light Pink for Healthy Tissue
        1: (0, 0, 0, 100),        # Black for Dead Tissue
        2: (173, 255, 47, 100),   # Greenish Yellow for Infected Tissue
    }

    # Create an empty overlay image
    overlay = np.zeros((image.shape[0], image.shape[1], 4), dtype=np.uint8)

    # Assign colors to each cluster
    for cluster, color in color_map.items():
        overlay[segmented_labels == cluster] = color

    # Convert overlay to BGR
    overlay_bgr = cv2.cvtColor(overlay[:, :, :3], cv2.COLOR_RGBA2BGR)

    # Blend the original image with the overlay (alpha blending)
    alpha = 0.3  # Adjust transparency
    blended = cv2.addWeighted(image, 1, overlay_bgr, alpha, 0)

    # Convert to PNG format
    is_success, buffer = cv2.imencode(".png", cv2.cvtColor(blended, cv2.COLOR_RGB2BGR))
    if not is_success:
        return None

    return buffer.tobytes()

@app.post("/segment/")
async def segment_upload(file: UploadFile = File(...)):
    image_bytes = await file.read()
    segmented_image = segment_image(image_bytes)

    if segmented_image is None:
        return JSONResponse(status_code=500, content={"error": "Image processing failed."})

    return Response(content=segmented_image, media_type="image/png")
