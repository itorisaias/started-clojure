(ns myapi.app
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as json]
            [ring.middleware.params :as params]))

(require '[myapi.api.user :as api-user])

(defroutes handler-api
           (->
             (routes api-user/user-routes
                     (route/not-found "resource not found"))
             (handler/api)
             params/wrap-params
             json/wrap-json-body))
