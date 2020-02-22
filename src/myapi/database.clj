(ns myapi.database)

(defn conn []
  (let [dbname (or (System/getenv "DB_NAME") "postgres")
        host (or (System/getenv "DB_HOST") "localhost")
        user (or (System/getenv "DB_USER") "postgres")
        password (or (System/getenv "DB_PASS") "mysecretpassword")]
    {:dbtype   "postgresql"
     :dbname   dbname
     :host     host
     :user     user
     :password password}))
