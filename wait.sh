#!/bin/bash
until mysqladmin ping -h"$mysql-nutrition" --silent; do
 echo "$(date) - waiting for database to start"
 sleep 2
done
echo "$(date) - connected successfully"
exec $@
