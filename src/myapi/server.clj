(ns myapi.server
  (:gen-class))

(defn start
  []
  (println "Starting server..."))

(defn stop
  []
  (println "Server stopped"))

(defn -main
  []
  (start))
