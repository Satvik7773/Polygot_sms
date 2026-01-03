package db

import (
	"context"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

func GetCollection() *mongo.Collection {
	client, _ := mongo.Connect(
		context.TODO(),
		options.Client().ApplyURI("mongodb://localhost:27017"),
	)
	return client.Database("smsdb").Collection("messages")
}
