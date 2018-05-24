#!/bin/bash

#export SDKMAN_DIR="/home/lucas/.sdkman"
#[[ -s "/home/lucas/.sdkman/bin/sdkman-init.sh" ]] && source "/home/lucas/.sdkman/bin/sdkman-init.sh"

bash db.sh

bash gradlew bootRun


