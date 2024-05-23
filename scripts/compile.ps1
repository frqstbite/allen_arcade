# Generate sources.txt
Get-ChildItem -Recurse -Name -Filter "*.java" | Out-File "sources.txt" -Encoding Ascii

# Compile
javac '@sources.txt' -d "../bin" -g