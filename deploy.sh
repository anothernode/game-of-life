#!/usr/bin/env bash

host="gibbs"
host_dir="game-of-life"
service="game-of-life"

build_path="build/libs/"

# This is a bit britle of course because it depends on the output of `printVersion` not to change,
# But this whole deployment should be scrapped in favor of a custom gradle task at some point
# anyway.
version=`./gradlew printVersion | head -n 3 | tail -n 1`

jar="game-of-life-${version}.jar"
file_path="${build_path}${jar}"

./gradlew build
scp -q $file_path ${host}:${host_dir}
ssh -q $host supervisorctl stop $service
ssh -q $host ln -sf $jar ${host_dir}/current.jar
ssh -q $host supervisorctl start $service
