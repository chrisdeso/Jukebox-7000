#!/bin/bash

# First, show what will be removed (dry run)
echo "Files that will be untracked (preview):"
git clean -fXd -n

# Show tracked files that will be removed from git's index
echo -e "\nTracked files that will be removed from git's index (preview):"
git rm -r --cached . -n

# Prompt for confirmation
read -p "Do you want to proceed with the cleanup? (y/N): " confirm

if [ "$confirm" = "y" ] || [ "$confirm" = "Y" ]; then
    # Remove tracked files from git's index
    git rm -r --cached .
    
    # Clean untracked files matching gitignore
    git clean -fXd
    
    # Re-add all files (this will respect the new .gitignore)
    git add .
    
    # Commit the changes
    git commit -m "Clean repository based on .gitignore"
    
    echo "Cleanup completed successfully!"
else
    echo "Cleanup cancelled."
fi
