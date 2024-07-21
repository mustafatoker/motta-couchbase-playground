set -m

 /entrypoint.sh couchbase-server &

 sleep 15

 couchbase-cli cluster-init -c couchbase:8091 \
   --cluster-username=${COUCHBASE_ADMINISTRATOR_USERNAME} \
   --cluster-password=${COUCHBASE_ADMINISTRATOR_PASSWORD} \
   --services=data,index,query,fts \
   --cluster-ramsize=512 \
   --cluster-index-ramsize=256 \
   --cluster-fts-ramsize=256

 curl -v http://couchbase:8091/settings/web -d port=8091 -d username="${COUCHBASE_ADMINISTRATOR_USERNAME}" -d password="${COUCHBASE_ADMINISTRATOR_PASSWORD}"

 sleep 15

 # Setup Bucket
 couchbase-cli bucket-create -c couchbase:8091 \
   --username=${COUCHBASE_ADMINISTRATOR_USERNAME} \
   --password=${COUCHBASE_ADMINISTRATOR_PASSWORD} \
   --bucket=motto_app \
   --bucket-type=couchbase \
   --bucket-ramsize=256

 fg 1