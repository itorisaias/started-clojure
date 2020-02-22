(ns myapi.api.user
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :refer :all]
            [clojure.tools.logging :as log]
            [myapi.services.userService :as service]
            [ring.middleware.json :as json]))

(defroutes user-routes
           (context "/user" []
             (GET "/" []
               (fn [_] {:status  200
                        :headers {"Content-Type" "text/json"}
                        :body    (generate-string (service/findAll))}))
             (GET "/:id" [id]
               (fn [_] {:status  200
                        :headers {"Content-Type" "text/json"}
                        :body    (generate-string (service/findById id))}))
             (POST "/" request
               (let [username (get-in request [:body "username"])
                     name (get-in request [:body "name"])
                     password (get-in request [:body "password"])]
                 (-> {:status  202
                      :headers {"Content-Type" "text/json"}
                      :body    (generate-string (service/create name username password))})))
             (PUT "/:id" request
               (let [username (get-in request [:body "username"])
                     name (get-in request [:body "name"])
                     id (:id (:params request))]
                 (-> {:status  200
                      :headers {"Content-Type" "text/json"}
                      :body    (generate-string (service/updateRecord id name username))})))
             (DELETE "/:id" [id]
               (fn [_] {:status  204
                        :headers {"Content-Type" "text/json"}
                        :body    (generate-string (service/delete id))}))
             )
           (route/not-found "You Must Be New Here"))