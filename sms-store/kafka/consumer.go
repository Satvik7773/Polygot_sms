package kafka

import (
	"context"
	"encoding/json"
	"log"
	"time"

	"github.com/segmentio/kafka-go"
	"sms-store/db"
	"sms-store/model"
)

func StartConsumer() {
	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers: []string{"localhost:9092"},
		Topic:   "sms-events",
		GroupID: "sms-store-group",
	})

	collection := db.GetCollection()

	for {
		msg, err := reader.ReadMessage(context.Background())
		if err != nil {
			log.Println(err)
			continue
		}

		var sms model.SMS
		json.Unmarshal(msg.Value, &sms)
		sms.Timestamp = time.Now()

		collection.InsertOne(context.TODO(), sms)
		log.Println("Stored SMS for:", sms.PhoneNumber)
	}
}
