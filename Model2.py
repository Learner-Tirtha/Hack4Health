import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.applications import MobileNetV2
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import GlobalAveragePooling2D, Dense, Dropout
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.callbacks import EarlyStopping, ReduceLROnPlateau, ModelCheckpoint
import os
import numpy as np
from tensorflow.keras.preprocessing import image

# ✅ 1️⃣ Define Dataset Paths
base_dir = "D:/Hack4Health/severity_Dataset"
train_dir = os.path.join(base_dir, 'train')
valid_dir = os.path.join(base_dir, 'valid')
test_dir = os.path.join(base_dir, 'test')

img_size = (224, 224)
batch_size = 32

# ✅ 2️⃣ Load Data Using ImageDataGenerator
train_datagen = ImageDataGenerator(rescale=1./255)
valid_test_datagen = ImageDataGenerator(rescale=1./255)

train_generator = train_datagen.flow_from_directory(
    train_dir, target_size=img_size, batch_size=batch_size, class_mode='categorical'
)

valid_generator = valid_test_datagen.flow_from_directory(
    valid_dir, target_size=img_size, batch_size=batch_size, class_mode='categorical'
)

test_generator = valid_test_datagen.flow_from_directory(
    test_dir, target_size=img_size, batch_size=batch_size, class_mode='categorical', shuffle=False
)

# ✅ 3️⃣ Load Pretrained MobileNetV2 & Freeze Some Layers
base_model = MobileNetV2(input_shape=(224, 224, 3), include_top=False, weights='imagenet')

# Freeze first 100 layers (fine-tuning only deeper layers)
for layer in base_model.layers[:100]:
    layer.trainable = False

# Build Fine-Tuned Model
model = Sequential([
    base_model,
    GlobalAveragePooling2D(),
    Dense(128, activation='relu'),
    Dropout(0.5),
    Dense(3, activation='softmax')  # 3 classes: Mild, Moderate, Severe
])

# Compile Model with a Lower Learning Rate for Fine-Tuning
model.compile(optimizer=Adam(learning_rate=0.0001),  
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# Print Model Summary
model.summary()

# ✅ 4️⃣ Define Callbacks (Early Stopping, LR Reduction, Model Checkpoint)
checkpoint_path = "D:/Hack4Health/saved_model/dfu_severity_mobilenetv2.h5"

callbacks = [
    EarlyStopping(monitor='val_loss', patience=5, restore_best_weights=True),
    ReduceLROnPlateau(monitor='val_loss', factor=0.2, patience=3, min_lr=1e-6),
    ModelCheckpoint(filepath=checkpoint_path, save_best_only=True, monitor='val_loss', mode='min')
]

# ✅ 5️⃣ Train the Model with Fine-Tuning
history = model.fit(
    train_generator,
    epochs=20,  # More epochs for fine-tuning
    validation_data=valid_generator,
    callbacks=callbacks
)

# ✅ 6️⃣ Evaluate on Test Data
test_loss, test_acc = model.evaluate(test_generator)
print(f"Test Accuracy: {test_acc * 100:.2f}%")

# ✅ 7️⃣ Save Final Model Weights
model.save_weights("D:/Hack4Health/dfu_severity_final_weights.h5")
print("Final model weights saved successfully!")
