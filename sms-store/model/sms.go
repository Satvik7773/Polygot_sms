package model

import "time"

type SMS struct {
	UserID      string    `bson:"userId" json:"userId"`
	PhoneNumber string    `bson:"phoneNumber" json:"phoneNumber"`
	Message     string    `bson:"message" json:"message"`
	Status      string    `bson:"status" json:"status"`
	Timestamp   time.Time `bson:"timestamp" json:"timestamp"`
}
