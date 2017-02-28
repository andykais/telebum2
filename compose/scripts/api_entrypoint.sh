#!/bin/sh

wait-for-it.sh postgres:5432

#echo "$CMD"
CMD="$@"
/bin/sh -c "$CMD"
