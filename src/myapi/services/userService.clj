(ns myapi.services.userService
  (:require [myapi.repositories.userRepository :as repository]))

(defn findAll
  []
  (repository/findAll))

(defn findById
  [id]
  (repository/findById id))

(defn create
  [name username password]
  (repository/createRecord name username password))

(defn updateRecord
  [id name username]
  (repository/updateRecord id name username))

(defn delete
  [id]
  (repository/deleteRecord id))
