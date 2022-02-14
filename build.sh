#!/bin/bash
set -euo pipefail
cd ${0%/*}

mvn clean package

docker build -t kaffeehaus .
