(defproject json-ip "1.0.0-SNAPSHOT"
  :description "Returns the requester's IP Address"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.1.8"]
                 [ring/ring-json "0.2.0"]
                 [ring/ring-jetty-adapter "1.1.8"]]
  :main json-ip.main)
