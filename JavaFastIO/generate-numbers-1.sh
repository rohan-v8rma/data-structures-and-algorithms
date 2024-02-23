#!/bin/bash

# Pass these numbers to any of the java executables like so: `java FastInput < input-1.txt`

echo "1 " > input-1.txt

for n in {2..1000000};
do
    # -n flag ensures same line
    echo "$n " >> input-1.txt
done