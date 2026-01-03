package handlers

import (
	"context"
	"encoding/json"
	"net/http"

	"go.mongodb.org/mongo-driver/bson"
	"sms-store/db"
)

func GetHistory(w http.ResponseWriter, r *http.Request) {
	userId := r.URL.Path[len("/v1/user/"):]

	collection := db.GetCollection()
	cursor, _ := collection.Find(context.TODO(), bson.M{"userId": userId})

	var result []map[string]interface{}
	cursor.All(context.TODO(), &result)

	json.NewEncoder(w).Encode(result)
}
