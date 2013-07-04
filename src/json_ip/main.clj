(ns json-ip.main
  (:use ring.util.response
        ring.middleware.json
        ring.adapter.jetty))

(defn handler [req]
  (response {:ip (:remote-addr req)}))

(def app
  (wrap-json-response handler))

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "VCAP_APP_PORT" "8080"))]
    (run-jetty app {:port port})))

