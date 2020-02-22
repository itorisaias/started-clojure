(ns myapi.repositories.userRepository
  (:require [myapi.database :as db]
            [clojure.java.jdbc :as jdbc]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all :as helper]))

(defn uuid-from-string [data]
  (java.util.UUID/fromString
    (clojure.string/replace data
                            #"(\w{8})(\w{4})(\w{4})(\w{4})(\w{12})"
                            "$1-$2-$3-$4-$5")))

(defn findAll
  []
  (jdbc/query (db/conn) (-> (select :*)
                            (from :users)
                            (where [:= :is_active true])
                            sql/format)))

(defn findById
  [id]
  (jdbc/query (db/conn) (let [uuid (uuid-from-string id)]
                          (-> (select :*)
                              (from :users)
                              (where [:= :id uuid] [:= :is_active true])
                              sql/format))))

(defn createRecord
  [name username password]
  (jdbc/execute! (db/conn) (let [id (java.util.UUID/randomUUID)
                                 now (sql/raw "NOW()")
                                 pass (str password)]
                             (-> (insert-into :users)
                                 (columns :id :name :username :password :is_active :created_at)
                                 (values
                                   [[id name username pass true now]])
                                 sql/format))))

(defn updateRecord
  [id name username]
  (jdbc/execute! (db/conn) (let [now (sql/raw "NOW()")
                                 uuid (uuid-from-string id)]
                             (-> (helper/update :users)
                                 (helper/sset {:name name
                                               :username   username
                                               :updated_at now})
                                 (where [:= :id uuid])
                                 sql/format))))

(defn deleteRecord
  [id]
  (jdbc/execute! (db/conn) (let [now (sql/raw "NOW()")
                                 uuid (uuid-from-string id)]
                             (-> (helper/update :users)
                                 (helper/sset {:is_active false
                                               :updated_at now})
                                 (where [:= :id uuid])
                                 sql/format))))
