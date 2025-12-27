
# Test access mongodb
```shell
#run at: mongodb://root:123456@localhost:27017
docker exec -it mongodb mongosh -u root -p 123456
test> show dbs
admin   40.00 KiB
config  60.00 KiB
local   72.00 KiB
```
```sql
db.myCollection.insertOne({ name: "Alice", age: NumberInt(25) });
db.myCollection.insertOne({ name: "Jupiter", age: NumberInt(28) });
-- db.myCollection.deleteOne({age:{$gt:25}});
db.myCollection.find({age:{$gt:25}});
```

# Common commands
|Operation|Command|
|---|---|
|查看日志|docker logs -f mongodb|
|进入容器|docker exec -it mongodb bash|
|查看容器|docker ps -a|
|停止容器|docker stop mongodb|


