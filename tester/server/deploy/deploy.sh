# if first argument is not there, throw error and exit
if [ -z "$1" ]
  then
    echo "No tag supplied, Usage: ./build.sh <tag>"
    exit 1
fi

./build.sh
docker buildx create --use
docker buildx build --load --platform linux/amd64 -t mehmaan:"$1" .
docker tag mehmaan:"$1" shibasispatnaik81/mehmaan:"$1"
docker push shibasispatnaik81/mehmaan:"$1"
gcloud run deploy --image docker.io/shibasispatnaik81/mehmaan:"$1" --region asia-south1
# --platform managed --region us-central1 --allow-unauthenticated --memory 512Mi --cpu 1 --max-instances 1 --timeout 60 --port 8080 --project mehmaan-2021
