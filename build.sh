#!/bin/bash
set -euo pipefail
cd ${0%/*}


docker build -t kaffeehaus .
