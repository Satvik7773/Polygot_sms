package main

import (
	"log"
	"net/http"

	"sms-store/handlers"
	"sms-store/kafka"
)

func main() {
	go kafka.StartConsumer()

	http.HandleFunc("/v1/user/", handlers.GetHistory)
	log.Println("SMS Store running on :8081")
	http.ListenAndServe(":8081", nil)
}
