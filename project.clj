(defproject myapi "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-json "0.5.0"]
                 [org.clojure/tools.logging "0.6.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [liberator "0.15.3"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [org.postgresql/postgresql "42.2.10.jre7"]
                 [honeysql "0.9.8"]
                 [cheshire "5.10.0"]
                 [http-kit "2.3.0"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler app/api-handler}
  :main ^:skip-aot myapi.server
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
