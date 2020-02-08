(ns myapi.server
  (:require [myapi.config :as config]
            [myapi.app :as app])
  (:gen-class))

(defn start
  []
  (println "Starting server...")
  (config/start)
  (app/start))

(defn stop
  []
  (println "Server stopped"))

(defn -main
  []
  (start))
