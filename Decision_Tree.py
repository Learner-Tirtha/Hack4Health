import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
from sklearn.tree import DecisionTreeClassifier
import tensorflow as tf
import joblib

# Load the dataset
df = pd.read_csv("diabetes_risk_dataset.csv")

# Encode categorical variables
label_encoders = {}
for column in df.columns[:-1]:  # Exclude target column
    if df[column].dtype == 'object':
        le = LabelEncoder()
        df[column] = le.fit_transform(df[column])
        label_encoders[column] = le

# Split data
X = df.drop(columns=["Diabetes Risk"])
y = df["Diabetes Risk"]
y = LabelEncoder().fit_transform(y)  # Encode target labels
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Train Decision Tree model
dt_model = DecisionTreeClassifier()
dt_model.fit(X_train, y_train)

# Save the trained model as .h5
joblib.dump(dt_model, "decision_tree_model.pkl")

# Convert to TensorFlow model
tf_model = tf.keras.Sequential([
    tf.keras.layers.Dense(64, activation='relu', input_shape=(X_train.shape[1],)),
    tf.keras.layers.Dense(32, activation='relu'),
    tf.keras.layers.Dense(len(set(y)), activation='softmax')
])

tf_model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])
tf_model.fit(X_train, y_train, epochs=50, batch_size=16, verbose=1)

tf_model.save("decision_tree_model.h5")
print("Model saved as decision_tree_model.h5")
