#!/bin/bash

# Create directories if they don't exist
mkdir -p bin lib

# Set the path to JavaFX - update this path if different on your system
JAVAFX_PATH="/usr/share/openjfx/lib"

# Download JSON library if not present
JSON_LIB="lib/json.jar"
if [ ! -f "$JSON_LIB" ]; then
    echo "Downloading JSON library..."
    curl -L "https://search.maven.org/remotecontent?filepath=org/json/json/20231013/json-20231013.jar" -o "$JSON_LIB"
fi

# Verify JavaFX installation
if [ ! -d "$JAVAFX_PATH" ]; then
    echo "JavaFX not found at $JAVAFX_PATH"
    echo "Please install JavaFX using:"
    echo "sudo apt install openjfx libopenjfx-java"
    exit 1
fi

# Clean previous build
rm -rf bin/*

# Find all Java files
JAVA_FILES=$(find src -name "*.java")

if [ -z "$JAVA_FILES" ]; then
    echo "No Java files found!"
    exit 1
fi

# Compile
echo "Compiling..."
echo "Found Java files:"
echo "$JAVA_FILES"

javac -cp "$JSON_LIB" \
      --module-path "$JAVAFX_PATH" \
      --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.web \
      -d bin $JAVA_FILES

if [ $? -eq 0 ]; then
    echo "Compilation successful. Running application..."
    
    echo "Running MusicAppMain..."
    
    # Run the application with both JavaFX and JSON library in classpath
    java -cp "bin:$JSON_LIB" \
         --module-path "$JAVAFX_PATH" \
         --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.web \
         MusicAppMain
else
    echo "Compilation failed!"
fi
