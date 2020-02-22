(ns myapi.server
  (:require [myapi.app :as app]
            [org.httpkit.server :as server])
  (:gen-class))

(defn -main
  "This is our app's entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (server/run-server #'app/handler-api {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))
