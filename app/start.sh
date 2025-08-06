!#/bin/bash

echo "1. build"
echo "2. run"
read -p "what to do? " ans

if [ "$ans" = '1' ]; then
    docker build -t myapp .
elif [ "$ans" = '2' ]; then
    docker run -p 5000:5000 myapp
else
  echo "wrong ans"
fi
