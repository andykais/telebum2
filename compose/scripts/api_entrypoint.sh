#!/bin/sh

cd /usr/src/app

wait-for-it.sh postgres:5432

CMD="$@"
echo "$CMD"
/bin/sh -c "$CMD"
