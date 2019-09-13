#!/bin/bash

# edit host:port to match your configuration

curl --form "file=@$1" localhost:8080/bulk/enroll
