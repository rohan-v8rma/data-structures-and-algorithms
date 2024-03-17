#!/bin/bash

# Pass these numbers to any of the java executables like so: `java FastInput < input-2.txt`

# -n flag ensures same line
echo -n "1 " > "input-2.txt"

for n in {2..1000000};
do
    # -n flag ensures same line
    echo -n "$n " >> "input-2.txt"
done